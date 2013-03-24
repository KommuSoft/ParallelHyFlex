package parallelhyflex.problemdependent.searchspace.negotation;

import java.util.Collection;
import java.util.HashSet;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class TwoSetWritableSearchSpaceNegotiator<TSolution extends Solution<TSolution>,TEC extends WritableEnforceableConstraint<TSolution>> implements WritableSearchSpaceNegotiator<TSolution,TEC> {

    @Override
    public SearchSpace<TSolution> negotiate(Collection<TEC> enforceableConstraints) {
        return null;
    }
    
    public Object[] generatePacket (Collection<TEC> enforceableConstraints) {
        
        return null;
    }
    
    public HashSet<TEC> readPacket () {
        return null;
        
    }
    
}
