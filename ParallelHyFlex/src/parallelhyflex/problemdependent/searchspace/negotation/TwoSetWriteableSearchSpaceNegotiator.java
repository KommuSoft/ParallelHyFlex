package parallelhyflex.problemdependent.searchspace.negotation;

import java.util.Collection;
import parallelhyflex.communication.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.searchspace.TwoSetSearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class TwoSetWriteableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>> extends MergingWritableSearchSpaceNegotiator<TSolution, TEC> {

    private final TwoSetSearchSpace<TSolution> searchSpace = new TwoSetSearchSpace<>();

    public TwoSetWriteableSearchSpaceNegotiator(ReadableGenerator<TEC> generator) {
        super(generator);
    }

    @Override
    protected SearchSpace<TSolution> innerNegotiate(Collection<TEC> own, Collection<TEC> others) {
        this.getSearchSpace().replacePositive(own);
        this.getSearchSpace().replaceNegative(others);
        return this.getSearchSpace();
    }

    /**
     * @return the searchSpace
     */
    public TwoSetSearchSpace<TSolution> getSearchSpace() {
        return searchSpace;
    }
}
