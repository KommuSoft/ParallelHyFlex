package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface EnforceableConstraint<TSolution extends Solution<TSolution>> extends Constraint<TSolution> {
    
    void enforceTrue (TSolution solution);
    void enforceFalse (TSolution solution);
    
}
