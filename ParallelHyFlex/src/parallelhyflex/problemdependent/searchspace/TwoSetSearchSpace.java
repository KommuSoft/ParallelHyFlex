package parallelhyflex.problemdependent.searchspace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class TwoSetSearchSpace<TSolution extends Solution<TSolution>> extends SearchSpaceBase<TSolution> {

    private static final Logger LOG = Logger.getLogger(TwoSetSearchSpace.class.getName());
    private final List<EnforceableConstraint<TSolution>> positive = new ArrayList<>();
    private final List<EnforceableConstraint<TSolution>> negative = new ArrayList<>();

    /**
     *
     * @param solution
     */
    @Override
    public void correct(TSolution solution) {
        for (EnforceableConstraint<TSolution> constraint : this.getPositive()) {
            constraint.enforceTrue(solution);
        }
        if (this.getNegative().size() > 0) {
            ProbabilityUtils.randomElement(this.getNegative()).enforceFalse(solution);
        }
    }

    /**
     *
     * @param solution
     * @return
     */
    @Override
    public boolean isInSearchSpace(TSolution solution) {
        for (EnforceableConstraint<TSolution> constraint : this.getPositive()) {
            if (!constraint.isSatisfied(solution)) {
                return false;
            }
        }
        for (EnforceableConstraint<TSolution> constraint : this.getNegative()) {
            if (!constraint.isSatisfied(solution)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the positive
     */
    public List<EnforceableConstraint<TSolution>> getPositive() {
        return positive;
    }

    /**
     *
     * @param positive
     */
    public void replacePositive(Collection<? extends EnforceableConstraint<TSolution>> positive) {
        this.positive.clear();
        this.positive.addAll(positive);
    }

    /**
     * @return the negative
     */
    public List<EnforceableConstraint<TSolution>> getNegative() {
        return negative;
    }

    /**
     *
     * @param negative
     */
    public void replaceNegative(Collection<? extends EnforceableConstraint<TSolution>> negative) {
        this.replaceNegative(negative, negative.size());
    }

    /**
     *
     * @param negative
     */
    public void replaceNegative(Collection<? extends EnforceableConstraint<TSolution>> negative, int n) {
        this.negative.clear();
        Iterator<? extends EnforceableConstraint<TSolution>> it = negative.iterator();
        for (int i = 0; i < n && it.hasNext(); i++) {
            this.negative.add(it.next());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('+');
        sb.append(this.positive.toString());
        sb.append('-');
        sb.append(this.negative.toString());
        return sb.toString();
    }
}
