package parallelhyflex.problems.frequencyassignment.heuristic;

import java.util.List;
import parallelhyflex.algebra.collections.ArrayToListWrapper;
import parallelhyflex.algebra.collections.ConstantInfiniteList;
import parallelhyflex.genetic.crossover.ProbabilityCrossover;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicC3 extends CrossoverHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentHeuristicC3(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from1
     * @param from2
     */
    @Override
    public void applyHeuristicLocally(FrequencyAssignmentSolution from1, FrequencyAssignmentSolution from2) {
        double pb = from1.getEvaluation();
        double pa = pb + from1.getEvaluation();
        pb /= pb;
        pa = 1.0d - pa;
        ManipulationObserver mo = new FrequencyAssignmentGuiderManipulator(this.getProblem(), from1);
        List<Integer> gn = new ConstantInfiniteList<>(0x01);
        List<Double> pc = new ArrayToListWrapper<>(new Double[]{pa, pb});
        int[][] fa = new int[][]{from1.getFrequencyAssignment(), from2.getFrequencyAssignment()};
        ProbabilityCrossover.getInstance().crossoverLocal(mo, gn, pc, fa);
    }
}