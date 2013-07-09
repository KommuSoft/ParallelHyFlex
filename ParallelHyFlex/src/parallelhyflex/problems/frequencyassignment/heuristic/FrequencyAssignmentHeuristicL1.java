package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.localsearch.UniformFirstImprovementLocalSearch;
import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicL1 extends LocalSearchHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentHeuristicL1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from) {
        UniformFirstImprovementLocalSearch.getInstance().localSearchLocal(new FrequencyAssignmentGuiderManipulator(this.getProblem(), from), from.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }
}
