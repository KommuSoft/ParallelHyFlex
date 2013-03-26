package parallelhyflex.problemdependent.searchspace.negotation;

import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface WritableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> extends SearchSpaceNegotiator<TSolution, TEC> {
}
