package parallelhyflex.problemdependent;

import parallelhyflex.ProblemPointerBase;

/**
 *
 * @author kommusoft
 */
public abstract class ExperienceBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements Experience<TSolution> {

    public ExperienceBase(TProblem problem) {
        super(problem);
    }
}
