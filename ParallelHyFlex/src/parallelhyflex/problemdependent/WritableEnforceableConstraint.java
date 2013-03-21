package parallelhyflex.problemdependent;

import parallelhyflex.communication.Writable;

/**
 *
 * @author kommusoft
 */
public interface WritableEnforceableConstraint<TSolution extends Solution<TSolution>> extends EnforceableConstraint<TSolution>, Writable {
    
}
