package parallelhyflex.problems.threesat.heuristics;

import java.util.HashSet;
import parallelhyflex.problemdependent.heuristics.RuinRecreateHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;

/**
 * A ruin-recreate heuristic where an entire block of 64 consecutive bits are
 * cleared and set to a new value based on a greedy approach
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicR2 extends RuinRecreateHeuristicBase<ThreeSatSolution, ThreeSatProblem> {
    
    public ThreeSatHeuristicR2(ThreeSatProblem problem) {
        super(problem);
    }
    
    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        long[] clauses = this.getProblem().getClauses();
        int ci = ClauseUtils.getFalseClauseIndex(from, clauses);
        long clause = clauses[ci];
        int maxval = 0x01 << ClauseUtils.degree(clause);
        int[] indices = ClauseUtils.getUniqueIndices(clause);
        HashSet<Integer> indhash = new HashSet<>();
        for (int i = 0; i < indices.length; i++) {
            indhash.add(indices[i]);
        }
        int[][] infl = this.getProblem().getInfluences();
        int[] infli;
        int oldunsat = 0;
        HashSet<Integer> influencedClauses = new HashSet<>();
        CompactBitArray cba = from.getCompactBitArray();
        for (int i = 0x00; i < indices.length; i++) {
            infli = infl[indices[i]];
            for (int j = 0; j < infli.length; j++) {
                int k = infli[j];
                if (cba.satisfiesClauseWithout(clauses[k], indhash) && influencedClauses.add(k) && !from.satisfiesClause(clauses[k])) {
                    oldunsat++;
                }
            }
        }
        int unsat;
        int minval = -0x01;
        int minunsat = Integer.MAX_VALUE;
        for (int val = 0x00; val < maxval; val++) {
            cba.setAll(indices, val);
            unsat = 0;
            for (Integer i : influencedClauses) {
                if (!cba.satisfiesClause(clauses[i])) {
                    unsat++;
                }
            }
            if (unsat < minunsat) {
                minunsat = unsat;
                minval = val;
            }
        }
        cba.setAll(indices, minval);
        from.addConfictingClauses(minunsat - oldunsat);
    }
}