package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.HeuristicType;
import parallelhyflex.problemdependent.heuristics.HeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicC1 extends HeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicC1(ThreeSatProblem problem) {
        super(problem, HeuristicType.Crossover);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
    }//Do nothing, this is a crossover heuristic

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from1, ThreeSatSolution from2) {
        //TODO: implement
        CompactBitArray cba1 = from1.getCompactBitArray();
        CompactBitArray cba2 = from2.getCompactBitArray();
        double prob = (double) from2.getConflictingClauses()/(from1.getConflictingClauses()+from2.getConflictingClauses());
        for(int i = 0; i < cba1.values.length; i++) {
            if(Utils.StaticRandom.nextDouble() < prob) {
                cba1.values[i] = cba2.values[i];
            }
        }
        from1.recalculateConflictingClauses(this.getProblem().getConstraints());//TODO: only recalculate influenced clauses
    }
}
