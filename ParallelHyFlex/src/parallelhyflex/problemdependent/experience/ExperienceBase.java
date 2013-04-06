package parallelhyflex.problemdependent.experience;

import parallelhyflex.algebra.ProblemPointerBase;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class ExperienceBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends EnforceableConstraint<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements Experience<TSolution, TEC> {

    public ExperienceBase(TProblem problem) {
        super(problem);
    }
}
