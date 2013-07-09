package parallelhyflex.algebra;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class ProblemPointerBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> implements ProblemPointer<TSolution, TProblem> {

    private final TProblem problem;

    /**
     *
     * @param problem
     */
    public ProblemPointerBase(TProblem problem) {
        this.problem = problem;
    }

    /**
     *
     * @return
     */
    @Override
    public TProblem getProblem() {
        return problem;
    }
}