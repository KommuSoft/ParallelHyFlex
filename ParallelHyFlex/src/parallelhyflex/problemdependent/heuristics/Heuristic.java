package parallelhyflex.problemdependent.heuristics;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class Heuristic<TSolution extends Solution<TSolution>> {
    
    private final HeuristicType type;
    
    public Heuristic (HeuristicType type) {
        this.type = type;
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
    
}
