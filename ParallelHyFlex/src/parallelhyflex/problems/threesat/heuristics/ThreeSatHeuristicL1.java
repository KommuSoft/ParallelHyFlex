package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
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

    public ThreeSatHeuristicL1(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength(), delta, j, np, nn;
        int[][] influences = this.getProblem().getInfluences();
        long[] constraints = this.getProblem().getConstraints();
        CompactBitArray cba = from.getCompactBitArray();
        boolean improved, nextrun;
        int kappa = (int) Math.round(Math.pow(this.getProblem().getV(), 1.0 - this.getDepthOfSearch()));
        do {
            nextrun = false;
            for (Integer i : Utils.getLimitedModuloEnumerable(Utils.StaticRandom.nextInt(n), kappa, n)) {
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
}
