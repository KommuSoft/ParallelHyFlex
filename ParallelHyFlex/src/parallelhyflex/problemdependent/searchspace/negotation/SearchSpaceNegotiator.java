package parallelhyflex.problemdependent.searchspace.negotation;

import java.util.Collection;
import parallelhyflex.communication.PacketReceiver;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface SearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends EnforceableConstraint<TSolution>> extends PacketReceiver {

    void sendEnforceableConstraints(Collection<TEC> enforceableConstraints);
    boolean isReady ();
    boolean isActive ();
    SearchSpace<TSolution> getSearchSpace ();
    
}
