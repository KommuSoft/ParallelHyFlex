package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL3 extends LocalSearchHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicL3(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength(), delta;
        int[][] influences = this.getProblem().getInfluences();
        long[] constraints = this.getProblem().getClauses();
        CompactBitArray cba = from.getCompactBitArray();
        int maxindex = -1, maximprove = 0;
        for (Integer i : Utils.sequenceModulo(Utils.nextInt(n), (int) Math.round(Math.pow(this.getProblem().getV(), 1.0 - this.getDepthOfSearch())), n)) {
            delta = ClauseUtils.calculateLoss(i, cba, constraints, influences[i]);
            if (delta < maximprove) {
                maximprove = delta;
                maxindex = i;
            }
        }
        if (maxindex != -1) {
            cba.swap(maxindex);
            from.addConfictingClauses(maximprove);
        }
    }
    
    @Override
    public boolean usesDepthOfSearch() {
        return true;
    }

}
