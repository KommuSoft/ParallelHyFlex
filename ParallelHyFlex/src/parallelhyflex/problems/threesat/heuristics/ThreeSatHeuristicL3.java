package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
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
        int n = from.getLength(), delta, j, np, nn;
        int[] tocheck;
        long[] constraints = this.getProblem().getConstraints();
        CompactBitArray cba = from.getCompactBitArray();
        boolean improved, nextrun;
        int maxindex = -1, maximprove = 0;
        for (Integer i : Utils.getLimitedModuloEnumerable(Utils.StaticRandom.nextInt(n), (int) Math.round(Math.pow(this.getProblem().getV(), 1.0 - this.getDepthOfSearch())), n)) {
            tocheck = this.getProblem().getInfluences()[i];
            delta = 0;
            np = tocheck[0];
            nn = tocheck.length;
            for (j = 1; j <= np; j++) {
                if (cba.willSwap(constraints[tocheck[j]], i)) {
                    delta++;
                }
            }
            for (; j < nn; j++) {
                if (cba.willSwap(constraints[tocheck[j]], i)) {
                    delta--;
                }
            }
            delta *= (cba.getBit(i) << 1) - 1;
            improved = delta < 0;
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
}
