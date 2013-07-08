package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.ProblemSolutionPointerManipulationObserverBase;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentUtils;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentManipulator extends ProblemSolutionPointerManipulationObserverBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    public FrequencyAssignmentManipulator(FrequencyAssignmentSolution solution, FrequencyAssignmentProblem problem) {
        super(solution, problem);
    }

    public FrequencyAssignmentManipulator(FrequencyAssignmentProblem problem, FrequencyAssignmentSolution solution) {
        super(problem, solution);
    }

    @Override
    public void modify(int index, int value) {
        this.getSolution().evaluationDelta(FrequencyAssignmentUtils.evaluateDelta(this.getProblem(), this.getSolution().getFrequencyAssignment(), index, value));
    }
}
