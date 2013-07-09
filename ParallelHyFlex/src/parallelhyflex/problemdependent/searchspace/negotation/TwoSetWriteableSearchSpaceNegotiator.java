package parallelhyflex.problemdependent.searchspace.negotation;

import java.util.Collection;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.communication.serialisation.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.searchspace.TwoSetSearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class TwoSetWriteableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>, TRG extends ReadableGenerator<TEC> & ArgumentCloneable<TProblem,TRG>> extends MergingWritableSearchSpaceNegotiator<TSolution, TEC, TRG> implements ArgumentCloneable<TProblem, TwoSetWriteableSearchSpaceNegotiator<TSolution, TProblem, TEC, TRG>> {

    private final TwoSetSearchSpace<TSolution> searchSpace = new TwoSetSearchSpace<>();

    /**
     *
     * @param generator
     */
    public TwoSetWriteableSearchSpaceNegotiator(TRG generator) {
        super(generator);
    }

    /**
     *
     * @param own
     * @param others
     * @return
     */
    @Override
    protected SearchSpace<TSolution> innerNegotiate(Collection<TEC> own, Collection<TEC> others) {
        this.getSearchSpace().replacePositive(own);
        this.getSearchSpace().replaceNegative(others);
        return this.getSearchSpace();
    }

    /**
     * @return the searchSpace
     */
    @Override
    public TwoSetSearchSpace<TSolution> getSearchSpace() {
        return searchSpace;
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public TwoSetWriteableSearchSpaceNegotiator<TSolution, TProblem, TEC, TRG> clone(TProblem argument) {
        return new TwoSetWriteableSearchSpaceNegotiator<>(this.getGenerator().clone(argument));
    }
}
