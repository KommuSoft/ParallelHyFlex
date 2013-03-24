package parallelhyflex.problemdependent.problem;

import parallelhyflex.problemdependent.distance.DistanceFunction;
import parallelhyflex.problemdependent.heuristics.Heuristic;
import parallelhyflex.communication.Writable;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public interface Problem<TSolution extends Solution<TSolution>> extends Writable {
    
    public Heuristic<TSolution> getHeuristic (int index);
    public int getNumberOfHeuristics ();
    public ObjectiveFunction<TSolution> getObjectiveFunction (int index);
    public int getNumberOfObjectiveFunctions ();
    public DistanceFunction<TSolution> getDistanceFunction (int index);
    public int getNumberOfDistanceFunctions ();
    public SolutionGenerator<TSolution> getSolutionGenerator ();
    public double getIntensityOfMutation();
    public double getDepthOfSearch();
    public void setIntensityOfMutation(double iom);
    public void setDepthOfSearch(double dos);
    
}
