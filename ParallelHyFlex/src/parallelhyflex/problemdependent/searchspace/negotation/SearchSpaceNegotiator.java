package parallelhyflex.problemdependent.searchspace.negotation;

import java.util.Collection;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface SearchSpaceNegotiator<TSolution extends Solution<TSolution>,TEC extends WritableEnforceableConstraint<TSolution>> {
    
    SearchSpace<TSolution> negotiate (Collection<TEC> enforceableConstraints);
    
}
