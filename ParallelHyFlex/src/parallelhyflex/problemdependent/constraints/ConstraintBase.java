package parallelhyflex.problemdependent.constraints;

import parallelhyflex.algebra.ProblemPointerBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class ConstraintBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements Constraint<TSolution> {

    /**
     *
     * @param problem
     */
    public ConstraintBase(TProblem problem) {
        super(problem);
    }

    /**
     *
     * @param solution
     * @return
     */
    @Override
    public boolean isNotSatisfied(TSolution solution) {
        return !this.isSatisfied(solution);
    }
}
