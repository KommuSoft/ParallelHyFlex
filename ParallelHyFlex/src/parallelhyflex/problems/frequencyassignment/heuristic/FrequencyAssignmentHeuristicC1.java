package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.genetic.crossover.UniformCrossover;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicC1 extends CrossoverHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentHeuristicC1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from1
     * @param from2
     */
    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from1, FrequencyAssignmentSolution from2) {
        System.out.println("Executed");
        UniformCrossover.getInstance().crossoverLocal(new FrequencyAssignmentGuiderManipulator(this.getProblem(),from1),new int[][]{from1.getFrequencyAssignment(), from2.getFrequencyAssignment()});
    }
}