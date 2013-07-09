package parallelhyflex.problemdependent.constraints;

import parallelhyflex.algebra.ProblemPointerBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class EnforceableConstraintGeneratorBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends EnforceableConstraint<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements EnforceableConstraintGenerator<TSolution, TEC> {

    /**
     *
     * @param problem
     */
    public EnforceableConstraintGeneratorBase(TProblem problem) {
        super(problem);
    }
}
