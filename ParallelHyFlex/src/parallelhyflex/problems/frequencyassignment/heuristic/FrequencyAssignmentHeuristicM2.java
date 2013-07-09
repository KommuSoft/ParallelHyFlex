package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.UniformMutation;
import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicM2 extends MutationHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentHeuristicM2(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from) {
        UniformMutation.getInstance().mutateLocal(new FrequencyAssignmentManipulator(this.getProblem(), from), from.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }
}
