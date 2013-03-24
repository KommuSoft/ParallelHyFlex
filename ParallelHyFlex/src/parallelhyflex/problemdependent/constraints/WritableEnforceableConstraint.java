package parallelhyflex.problemdependent.constraints;

import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.communication.Writable;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface WritableEnforceableConstraint<TSolution extends Solution<TSolution>> extends EnforceableConstraint<TSolution>, Writable {
    
}
