package parallelhyflex.problemdependent.experience;

import parallelhyflex.algebra.ProblemPointerBase;
import parallelhyflex.experiencestorage.InstanceHypothesisGenerator;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class InstanceHypothesisGeneratorBase<TSolution extends Solution<TSolution>, THypothesis, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements InstanceHypothesisGenerator<TSolution, THypothesis> {

    /**
     *
     * @param problem
     */
    public InstanceHypothesisGeneratorBase(TProblem problem) {
        super(problem);
    }
}
