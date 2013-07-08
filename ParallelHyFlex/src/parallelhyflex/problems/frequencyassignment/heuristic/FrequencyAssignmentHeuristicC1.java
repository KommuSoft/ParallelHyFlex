package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.UniformCrossover;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicC1 extends CrossoverHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    public FrequencyAssignmentHeuristicC1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from1, FrequencyAssignmentSolution from2) {
        UniformCrossover.getInstance().crossoverLocal(new FrequencyAssignmentManipulator(this.getProblem(),from1),new int[][]{from1.getFrequencyAssignment(), from2.getFrequencyAssignment()});
    }
}