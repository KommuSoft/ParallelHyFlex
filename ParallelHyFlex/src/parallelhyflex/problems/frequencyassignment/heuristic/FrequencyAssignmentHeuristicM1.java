package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.mutation.InferenceNeighbourhoodBasedMutation;
import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicM1 extends MutationHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentHeuristicM1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from) {
        InferenceNeighbourhoodBasedMutation.getInstance().mutateLocal(this.getProblem().getObserver(from), this.getProblem().getInterferenceStructure(), from.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }
}
