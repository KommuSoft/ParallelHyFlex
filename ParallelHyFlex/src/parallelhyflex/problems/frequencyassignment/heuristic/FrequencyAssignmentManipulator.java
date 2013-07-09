package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.observer.ProblemSolutionPointerManipulationObserverBase;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentUtils;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentManipulator extends ProblemSolutionPointerManipulationObserverBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param solution
     * @param problem
     */
    public FrequencyAssignmentManipulator(FrequencyAssignmentSolution solution, FrequencyAssignmentProblem problem) {
        super(solution, problem);
    }

    /**
     *
     * @param problem
     * @param solution
     */
    public FrequencyAssignmentManipulator(FrequencyAssignmentProblem problem, FrequencyAssignmentSolution solution) {
        super(problem, solution);
    }

    /**
     *
     * @param index
     * @param value
     */
    @Override
    public void modify(int index, int value) {
        this.getSolution().evaluationDelta(FrequencyAssignmentUtils.evaluateDelta(this.getProblem(), this.getSolution().getFrequencyAssignment(), index, value));
    }
}
