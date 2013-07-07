package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicM2 extends MutationHeuristicBase<FrequencyAssignmentSolution,FrequencyAssignmentProblem> {

    public FrequencyAssignmentHeuristicM2(FrequencyAssignmentProblem problem) {
        super(problem);
    }
    
    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from) {
        //TODO: recalc evaluation
    }
    
}
