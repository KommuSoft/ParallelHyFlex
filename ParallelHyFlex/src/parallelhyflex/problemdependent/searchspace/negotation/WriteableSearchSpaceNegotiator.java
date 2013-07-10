package parallelhyflex.problemdependent.searchspace.negotation;

import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface WriteableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends WriteableEnforceableConstraint<TSolution>> extends SearchSpaceNegotiator<TSolution, TEC> {
}
