package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
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
        int n = from.getLength(), i, delta, j, np, nn;
        int[] tocheck;
        long[] constraints = this.getProblem().getConstraints();
        CompactBitArray cba = from.getCompactBitArray();
        boolean improved, nextrun;
        int kappa = (int) Math.round(Math.pow(this.getProblem().getV(), 1.0-this.getDepthOfSearch()));
        do {
            nextrun = false;
            for (i = Utils.StaticRandom.nextInt(kappa); i < n; i += kappa) {
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
                if (improved) {
                    cba.swap(i);
                    from.addConfictingClauses(delta);
                }
                nextrun |= improved;
            }
        } while (nextrun);
    }
}
