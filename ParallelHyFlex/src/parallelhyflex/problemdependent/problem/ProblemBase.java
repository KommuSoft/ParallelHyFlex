package parallelhyflex.problemdependent.problem;

import parallelhyflex.problemdependent.distance.DistanceFunction;
import parallelhyflex.problemdependent.heuristic.Heuristic;
import parallelhyflex.problemdependent.heuristic.HeuristicType;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemBase<TSolution extends Solution<TSolution>, TSolGen extends SolutionGenerator<TSolution>> implements Problem<TSolution> {

    private double intensityOfMutation = 0.1d;
    private double depthOfSearch = 0.1d;
    private Heuristic[] heuristics;
    private ObjectiveFunction[] objectives;
    private DistanceFunction[] distances;
    private TSolGen solutionGenerator;
    private final int[] ns;

    protected ProblemBase() {
        ns = new int[HeuristicType.values().length];
    }

    protected ProblemBase(Heuristic[] heuristics, ObjectiveFunction[] objectives, DistanceFunction[] distances, TSolGen solutionGenerator) {
        this();
        this.heuristics = heuristics;
        this.objectives = objectives;
        this.distances = distances;
        this.solutionGenerator = solutionGenerator;
    }

    @Override
    public double getIntensityOfMutation() {
        return this.intensityOfMutation;
    }

    @Override
    public double getDepthOfSearch() {
        return this.depthOfSearch;
    }

    @Override
    public void setIntensityOfMutation(double intensityOfMutation) {
        this.intensityOfMutation = Math.max(0.0d, Math.min(0.9999d, intensityOfMutation));
    }

    @Override
    public void setDepthOfSearch(double depthOfSearch) {
        this.depthOfSearch = Math.max(0.0d, Math.min(0.9999d, depthOfSearch));
    }

    @Override
    public Heuristic<TSolution> getHeuristic(int index) {
        return (Heuristic<TSolution>) this.heuristics[index];
    }

    @Override
    public ObjectiveFunction<TSolution> getObjectiveFunction(int index) {
        return (ObjectiveFunction<TSolution>) this.objectives[index];
    }
    
    @Override
    public ObjectiveFunction<TSolution> getObjectiveFunction() {
        return this.getObjectiveFunction(0);
    }

    @Override
    public DistanceFunction<TSolution> getDistanceFunction(int index) {
        return (DistanceFunction<TSolution>) this.distances[index];
    }

    @Override
    public TSolGen getSolutionGenerator() {
        return this.solutionGenerator;
    }

    @Override
    public int getNumberOfDistanceFunctions() {
        return this.distances.length;
    }

    @Override
    public int getNumberOfHeuristics() {
        return this.heuristics.length;
    }

    @Override
    public int getNumberOfHeuristicsOfType(HeuristicType type) {
        return this.ns[type.ordinal()];
    }

    @Override
    public int getNumberOfLocalSearchHeuristics() {
        return this.getNumberOfHeuristicsOfType(HeuristicType.LocalSearch);
    }

    @Override
    public int getNumberOfMutationHeuristics() {
        return this.getNumberOfHeuristicsOfType(HeuristicType.Mutation);
    }

    @Override
    public int getNumberOfCrossoverHeuristics() {
        return this.getNumberOfHeuristicsOfType(HeuristicType.Crossover);
    }

    @Override
    public int getNumberOfRuinRecreateHeuristics() {
        return this.getNumberOfHeuristicsOfType(HeuristicType.RuinRecreate);
    }

    @Override
    public HeuristicType getHeuristicType(int heuristic) {
        return this.heuristics[heuristic].getType();
    }

    @Override
    public int getNumberOfObjectiveFunctions() {
        return this.objectives.length;
    }

    /**
     * @param heuristics the heuristics to set
     */
    protected void setHeuristics(Heuristic[] heuristics) {
        this.heuristics = heuristics;
        for (Heuristic heu : heuristics) {
            ns[heu.getType().ordinal()]++;
        }
    }

    /**
     * @param objectives the objectives to set
     */
    protected void setObjectives(ObjectiveFunction[] objectives) {
        this.objectives = objectives;
    }

    /**
     * @param distances the distances to set
     */
    protected void setDistances(DistanceFunction[] distances) {
        this.distances = distances;
    }

    /**
     * @param solutionGenerator the solutionGenerator to set
     */
    protected void setSolutionGenerator(TSolGen solutionGenerator) {
        this.solutionGenerator = solutionGenerator;
    }
}
