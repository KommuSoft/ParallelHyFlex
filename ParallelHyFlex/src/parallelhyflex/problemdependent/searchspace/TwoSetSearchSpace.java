package parallelhyflex.problemdependent.searchspace;

import java.util.ArrayList;
import java.util.Collection;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class TwoSetSearchSpace<TSolution extends Solution<TSolution>> extends SearchSpaceBase<TSolution> {

    private final ArrayList<EnforceableConstraint<TSolution>> positive = new ArrayList<>();
    private final ArrayList<EnforceableConstraint<TSolution>> negative = new ArrayList<>();

    @Override
    public void correct(TSolution solution) {
        for (EnforceableConstraint<TSolution> constraint : this.getPositive()) {
            constraint.enforceTrue(solution);
        }
        if (this.getNegative().size() > 0) {
            ProbabilityUtils.randomElement(this.getNegative()).enforceFalse(solution);
        }
    }

    @Override
    public boolean isInSearchSpace(TSolution solution) {
        for (EnforceableConstraint<TSolution> constraint : this.getNegative()) {
            if (constraint.isSatisfied(solution)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the positive
     */
    public ArrayList<EnforceableConstraint<TSolution>> getPositive() {
        return positive;
    }

    public void replacePositive(Collection<? extends EnforceableConstraint<TSolution>> positive) {
        this.positive.clear();
        this.positive.addAll(positive);
    }

    /**
     * @return the negative
     */
    public ArrayList<EnforceableConstraint<TSolution>> getNegative() {
        return negative;
    }

    public void replaceNegative(Collection<? extends EnforceableConstraint<TSolution>> positive) {
        this.negative.clear();
        this.negative.addAll(positive);
    }
}
