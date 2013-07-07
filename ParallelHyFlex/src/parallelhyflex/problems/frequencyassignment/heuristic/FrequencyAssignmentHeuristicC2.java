package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.InterferenceCrossover;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicC2 extends CrossoverHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    public FrequencyAssignmentHeuristicC2(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from1, FrequencyAssignmentSolution from2) {
        InterferenceCrossover.getInstance().crossoverLocal(this.getProblem().getInterferenceStructure(), new int[][]{from1.getFrequencyAssignment(), from2.getFrequencyAssignment()});
        //TODO: recalc score
    }
}