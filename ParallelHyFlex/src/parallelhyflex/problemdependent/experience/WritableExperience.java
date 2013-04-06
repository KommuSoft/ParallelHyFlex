package parallelhyflex.problemdependent.experience;

import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface WritableExperience<TSolution extends Solution<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> extends Experience<TSolution, TEC> {
}
