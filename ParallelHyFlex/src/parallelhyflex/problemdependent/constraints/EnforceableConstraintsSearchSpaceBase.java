package parallelhyflex.problemdependent.constraints;

import java.util.ArrayList;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.SearchSpaceBase;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class EnforceableConstraintsSearchSpaceBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends SearchSpaceBase<TSolution,TProblem> {
    
    private final ArrayList<EnforceableConstraint<TSolution>> positive = new ArrayList<>(), negative = new ArrayList<>();
    
    public EnforceableConstraintsSearchSpaceBase (TProblem problem) {
        super(problem);
    }

    @Override
    public void correct(TSolution solution) {
        for(EnforceableConstraint<TSolution> constraint : this.getNegative()) {
            constraint.enforceFalse(solution);
        }
        if(this.getPositive().size() > 0) {
            ProbabilityUtils.randomElement(getPositive()).enforceTrue(solution);
        }
    }

    @Override
    public boolean isInSearchSpace(TSolution solution) {
        for(EnforceableConstraint<TSolution> constraint : this.getNegative()) {
            if(constraint.isSatisfied(solution)) {
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

    /**
     * @return the negative
     */
    public ArrayList<EnforceableConstraint<TSolution>> getNegative() {
        return negative;
    }
    
}
