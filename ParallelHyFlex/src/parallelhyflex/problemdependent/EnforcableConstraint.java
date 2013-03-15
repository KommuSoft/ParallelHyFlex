package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface EnforcableConstraint<TSolution extends Solution<TSolution>> extends Constraint<TSolution> {
    
    void enforceTrue (TSolution solution);
    void enforceFalse (TSolution solution);
    
}
