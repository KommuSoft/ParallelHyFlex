package parallelhyflex.problems.threesat.heuristic;

import java.util.HashSet;
import parallelhyflex.problemdependent.heuristic.RuinRecreateHeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 * A ruin-recreate heuristic where an entire block of 64 consecutive bits are
 * cleared and set to a new value based on a greedy approach where the number of
 * satisfiable clauses (if positive or negative) is maximized.
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicR1 extends RuinRecreateHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicR1(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        CompactBitArray cba = from.getCompactBitArray();
        int iblock = Utils.nextInt(cba.getBlockLength()), ioff = iblock << 6, j;
        long[] constraints = this.getProblem().getClauses();
        int[] binf = this.getProblem().getBlockInfluences()[iblock];
        int[][] inf = this.getProblem().getInfluences();
        int oldfail = 0;
        for (int i = 0; i < binf.length; i++) {
            if (!cba.satisfiesClause(constraints[binf[i]])) {
                oldfail++;
            }
        }

        HashSet<Integer> activeConstraints = new HashSet<>();
        int ci;
        for (int i = 0; i < binf.length; i++) {
            ci = binf[i];
            if (!cba.satisfiesClauseWithoutBlock(constraints[ci], iblock)) {
                activeConstraints.add(ci);
            }
        }
        int np, nn, npa, nna;
        int[] tocheck;
        for (int fixindex = Math.min(from.getLength() - 1, ioff + 63); fixindex >= ioff; fixindex--) {
            tocheck = inf[fixindex];
            np = tocheck[0];
            nn = tocheck.length;
            npa = 0;
            nna = 0;
            for (j = 1; j <= np; j++) {
                if (activeConstraints.contains(tocheck[j])) {
                    npa++;
                }
            }
            for (; j < nn; j++) {
                if (activeConstraints.contains(tocheck[j])) {
                    nna++;
                }
            }
            cba.set(fixindex, npa >= nna);
            if (npa >= nna) {
                for (j = 1; j <= np; j++) {
                    activeConstraints.remove(tocheck[j]);
                }
            } else {
                for (j = np + 1; j < nn; j++) {
                    activeConstraints.remove(tocheck[j]);
                }
            }
        }

        int newfail = 0;
        for (int i = 0; i < binf.length; i++) {
            if (!cba.satisfiesClause(constraints[binf[i]])) {
                newfail++;
            }
        }
        //TODO: speedup with delta on activeConstraints
        from.addConfictingClauses(newfail - oldfail);
    }
}