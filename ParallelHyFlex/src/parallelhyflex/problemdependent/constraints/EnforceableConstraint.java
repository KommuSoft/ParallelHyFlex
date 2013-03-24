package parallelhyflex.problemdependent.constraints;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface EnforceableConstraint<TSolution extends Solution<TSolution>> extends Constraint<TSolution> {
    
    void enforceTrue (TSolution solution);
    void enforceFalse (TSolution solution);
    
}
