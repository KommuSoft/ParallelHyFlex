package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public abstract class Heuristic<TSolution extends Solution> {
    
    private final HeuristicType type;
    
    public Heuristic (HeuristicType type) {
        this.type = type;
    }
    
    public abstract TSolution applyHeuristic (TSolution from);
    public abstract TSolution applyHeuristic (TSolution from1, TSolution from2);

    /**
     * @return the type
     */
    public HeuristicType getType() {
        return type;
    }
    
}
