package parallelhyflex.algebra;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

public class ProblemSolutionPointerBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements ProblemSolutionPointer<TSolution, TProblem> {

    private final TSolution solution;

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
        return this.getSolution();
    }
}
