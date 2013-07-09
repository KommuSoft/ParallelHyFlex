package parallelhyflex.problemdependent.constraints;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class WritableEnforceableConstraintBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends EnforceableConstraintBase<TSolution, TProblem> implements WritableEnforceableConstraint<TSolution> {

    /**
     *
     * @param problem
     */
    public WritableEnforceableConstraintBase(TProblem problem) {
        super(problem);
    }
}