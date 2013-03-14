package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface Problem<TSolution extends Solution<TSolution>> {
    
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
