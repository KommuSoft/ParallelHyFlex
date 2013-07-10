package parallelhyflex.problemdependent.constraints;

import parallelhyflex.communication.serialisation.Writeable;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface WriteableEnforceableConstraint<TSolution extends Solution<TSolution>> extends EnforceableConstraint<TSolution>, Writeable {
}
