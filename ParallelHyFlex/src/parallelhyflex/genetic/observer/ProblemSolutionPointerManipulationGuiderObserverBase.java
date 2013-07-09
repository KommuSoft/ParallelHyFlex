package parallelhyflex.genetic.observer;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemSolutionPointerManipulationGuiderObserverBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemSolutionPointerManipulationObserverBase<TSolution, TProblem> implements ManipulationGuiderObserver {

    public ProblemSolutionPointerManipulationGuiderObserverBase(TSolution solution, TProblem problem) {
        super(solution, problem);
    }

    public ProblemSolutionPointerManipulationGuiderObserverBase(TProblem problem, TSolution solution) {
        super(problem, solution);
    }
}
