package parallelhyflex.problemdependent.experience;

import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @param <TSolution> 
 * @param <TEC> 
 * @author kommusoft
 */
public interface WriteableExperience<TSolution extends Solution<TSolution>, TEC extends WriteableEnforceableConstraint<TSolution>> extends Experience<TSolution, TEC> {
}
