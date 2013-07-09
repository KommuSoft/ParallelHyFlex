package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.observer.ProblemSolutionPointerManipulationGuiderObserverBase;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentUtils;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentGuiderManipulator extends ProblemSolutionPointerManipulationGuiderObserverBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param solution
     * @param problem
     */
    public FrequencyAssignmentGuiderManipulator(FrequencyAssignmentSolution solution, FrequencyAssignmentProblem problem) {
        super(solution, problem);
    }

    /**
     *
     * @param problem
     * @param solution
     */
    public FrequencyAssignmentGuiderManipulator(FrequencyAssignmentProblem problem, FrequencyAssignmentSolution solution) {
        super(problem, solution);
    }

    /**
     *
     * @param index
     * @param value
     */
    @Override
    public void modify(int index, int value) {
        this.getSolution().interferenceDelta(this.calculateDelta(index, value));
        this.getSolution().nConfictsDelta(FrequencyAssignmentUtils.calculateNConflicts(this.getProblem(), this.getSolution().getFrequencyAssignment()));
    }

    @Override
    public double calculateDelta(int index, int value) {
        return FrequencyAssignmentUtils.calculateInterferenceDelta(this.getProblem(), this.getSolution().getFrequencyAssignment(), index, value);
    }
}
