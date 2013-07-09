package parallelhyflex.genetic.observer;

import parallelhyflex.algebra.ProblemSolutionPointerBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 * @param <TSolution>
 * @param <TProblem>
 */
public abstract class ProblemSolutionPointerManipulationObserverBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemSolutionPointerBase<TSolution, TProblem> implements ManipulationObserver {

    /**
     *
     * @param solution
     * @param problem
     */
    public ProblemSolutionPointerManipulationObserverBase(TSolution solution, TProblem problem) {
        super(solution, problem);
    }

    /**
     *
     * @param problem
     * @param solution
     */
    public ProblemSolutionPointerManipulationObserverBase(TProblem problem, TSolution solution) {
        super(problem, solution);
    }
}
