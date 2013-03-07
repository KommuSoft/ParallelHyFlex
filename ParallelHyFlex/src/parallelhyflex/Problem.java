package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public interface Problem<TSolution extends Solution> {
    
    public Heuristic<TSolution> getHeuristic (int index);
    public int getNumberOfHeuristics ();
    public ObjectiveFunction<TSolution> getObjectiveFunction (int index);
    public int getNumberOfObjectiveFunctions ();
    
}
