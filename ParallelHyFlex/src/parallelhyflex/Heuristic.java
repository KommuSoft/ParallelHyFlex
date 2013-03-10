package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public abstract class Heuristic<TSolution extends Solution<TSolution>> {
    
    private final HeuristicType type;
    private final Problem<TSolution> problem;
    
    public Heuristic (Problem<TSolution> problem, HeuristicType type) {
        this.type = type;
        this.problem = problem;
    }
    
    public TSolution applyHeuristic (TSolution from) {
        TSolution clone = from.clone();
        applyHeuristicLocally(clone);
        return clone;
    }
    public abstract void applyHeuristicLocally (TSolution from);
    public TSolution applyHeuristic (TSolution from1, TSolution from2) {
        TSolution clone = from1.clone();
        applyHeuristicLocally(clone,from2);
        return clone;
    }
    public void applyHeuristicLocally (TSolution from1, TSolution from2) {
        applyHeuristicLocally(from1);
    }

    /**
     * @return the type
     */
    public HeuristicType getType() {
        return type;
    }

    /**
     * @return the problem
     */
    public Problem getProblem() {
        return problem;
    }
    
    public double getIntensityOfMutation () {
        return this.getProblem().getIntensityOfMutation();
    }
    public double getDepthOfSearch () {
        return this.getProblem().getDepthOfSearch();
    }
    
}
