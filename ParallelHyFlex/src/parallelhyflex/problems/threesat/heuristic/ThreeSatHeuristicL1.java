package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 * A local search heurstic, where the system looks for a bit when swapped
 * increases the number of satisfied constraints.
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL1 extends LocalSearchHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    /**
     *
     * @param problem
     */
    public ThreeSatHeuristicL1(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength(), delta, j, np, nn;
        int[][] influences = this.getProblem().getInfluences();
        long[] constraints = this.getProblem().getClauses();
        CompactBitArray cba = from.getCompactBitArray();
        boolean improved, nextrun;
        int kappa = (int) Math.round(Math.pow(this.getProblem().getV(), 1.0 - this.getDepthOfSearch()));
        do {
            nextrun = false;
            for (Integer i : Utils.sequenceModulo(Utils.nextInt(n), kappa, n)) {
                delta = ClauseUtils.calculateLoss(i, cba, constraints, influences[i]);
                improved = delta < 0;
                if (improved) {
                    cba.swap(i);
                    from.addConfictingClauses(delta);
                }
                nextrun |= improved;
            }
        } while (nextrun);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean usesDepthOfSearch() {
        return true;
    }
}
