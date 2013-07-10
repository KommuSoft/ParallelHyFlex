package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.localsearch.UniformBestImprovementLocalSearch;
import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicL2 extends LocalSearchHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentHeuristicL2(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from) {
        UniformBestImprovementLocalSearch.getInstance().localSearchLocal(this.getProblem().getObserver(from), from.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }
}
