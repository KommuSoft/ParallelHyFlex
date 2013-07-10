package parallelhyflex.problemdependent.constraints;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class WriteableEnforceableConstraintBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends EnforceableConstraintBase<TSolution, TProblem> implements WriteableEnforceableConstraint<TSolution> {

    public WriteableEnforceableConstraintBase(TProblem problem) {
        super(problem);
    }
}