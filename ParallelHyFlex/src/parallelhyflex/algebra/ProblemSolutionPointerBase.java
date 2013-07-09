package parallelhyflex.algebra;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 * @param <TSolution>
 * @param <TProblem>
 */
public class ProblemSolutionPointerBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements ProblemSolutionPointer<TSolution, TProblem> {

    private final TSolution solution;

    /**
     *
     * @param solution
     * @param problem
     */
    public ProblemSolutionPointerBase(TSolution solution, TProblem problem) {
        this(problem, solution);
    }

    /**
     *
     * @param problem
     * @param solution
     */
    public ProblemSolutionPointerBase(TProblem problem, TSolution solution) {
        super(problem);
        this.solution = solution;
    }

    /**
     *
     * @return
     */
    @Override
    public TSolution getSolution() {
        return this.solution;
    }
}
