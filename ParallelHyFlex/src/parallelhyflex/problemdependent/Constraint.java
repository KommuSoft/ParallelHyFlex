package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface Constraint<TSolution extends Solution<TSolution>> {
    
    boolean isSatisfied (TSolution solution);
    boolean isNotSatisfied (TSolution solution);
    
}
