package parallelhyflex.problemdependent.heuristic;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class Heuristic<TSolution extends Solution<TSolution>> {

    private final HeuristicType type;

    /**
     *
     * @param type
     */
    public Heuristic(HeuristicType type) {
        this.type = type;
    }

    /**
     *
     * @param from
     * @return
     */
    public TSolution applyHeuristic(TSolution from) {
        TSolution clone = from.clone();
        applyHeuristicLocally(clone);
        return clone;
    }

    /**
     *
     * @param from
     */
    public abstract void applyHeuristicLocally(TSolution from);

    /**
     *
     * @param from1
     * @param from2
     * @return
     */
    public TSolution applyHeuristic(TSolution from1, TSolution from2) {
        TSolution clone = from1.clone();
        applyHeuristicLocally(clone, from2);
        return clone;
    }

    /**
     *
     * @param from1
     * @param from2
     */
    public void applyHeuristicLocally(TSolution from1, TSolution from2) {
        applyHeuristicLocally(from1);
    }

    /**
     * @return the type
     */
    public HeuristicType getType() {
        return type;
    }
    
    /**
     *
     * @return
     */
    public boolean usesDepthOfSearch () {
        return false;
    }
    /**
     *
     * @return
     */
    public boolean usesIntensityOfMutation () {
        return false;
    }
    
}
