package parallelhyflex.genetic;

import parallelhyflex.algebra.ProblemSolutionPointerBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

public abstract class ProblemSolutionPointerManipulationObserverBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemSolutionPointerBase<TSolution, TProblem> implements ManipulationObserver {

    public ProblemSolutionPointerManipulationObserverBase(TSolution solution, TProblem problem) {
        super(solution, problem);
    }

    public ProblemSolutionPointerManipulationObserverBase(TProblem problem, TSolution solution) {
        super(problem, solution);
    }
}
