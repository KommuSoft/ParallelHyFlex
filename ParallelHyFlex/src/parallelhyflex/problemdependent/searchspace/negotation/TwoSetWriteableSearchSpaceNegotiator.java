package parallelhyflex.problemdependent.searchspace.negotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.communication.serialisation.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraint;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.searchspace.TwoSetSearchSpace;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @param <TSolution>
 * @param <TProblem>
 * @param <TEC>
 * @param <TRG>
 * @author kommusoft
 */
public class TwoSetWriteableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends WriteableEnforceableConstraint<TSolution>, TRG extends ReadableGenerator<TEC> & ArgumentCloneable<TProblem, TRG>> extends MergingWriteableSearchSpaceNegotiator<TSolution, TEC, TRG> implements ArgumentCloneable<TProblem, TwoSetWriteableSearchSpaceNegotiator<TSolution, TProblem, TEC, TRG>> {

    private static final Logger LOG = Logger.getLogger(TwoSetWriteableSearchSpaceNegotiator.class.getName());
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
        int k = Math.min(own.size(),others.size());
        this.getSearchSpace().replacePositive(own);
        ArrayList<TEC> al = new ArrayList<>(others);
        ProbabilityUtils.shuffle(al,k);
        this.getSearchSpace().replaceNegative(others,k);
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
