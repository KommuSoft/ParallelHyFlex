package parallelhyflex.problemdependent.experience;

import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;

/**
 *
 * @author kommusoft
 */
public interface WritableExperience<TSolution extends Solution<TSolution>,TEC extends WritableEnforceableConstraint<TSolution>> extends Experience<TSolution,TEC> {
    
}
