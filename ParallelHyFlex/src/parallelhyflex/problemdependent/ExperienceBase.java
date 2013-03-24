package parallelhyflex.problemdependent;

import parallelhyflex.ProblemPointerBase;

/**
 *
 * @author kommusoft
 */
public abstract class ExperienceBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, TEC extends EnforceableConstraint<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements Experience<TSolution, TEC> {

    public ExperienceBase(TProblem problem) {
        super(problem);
    }
}
