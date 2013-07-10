package parallelhyflex.problems.threesat.heuristics;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL2 extends LocalSearchHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    /**
     *
     * @param problem
     */
    public ThreeSatHeuristicL2(ThreeSatProblem problem) {
        super(problem);
    }
    
    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        int n = from.getLength(), delta;
        long[] constraints = this.getProblem().getClauses();
        int[][] influences = this.getProblem().getInfluences();
        CompactBitArray cba = from.getCompactBitArray();
        boolean improved;
        for(Integer i : Utils.sequenceModulo(Utils.StaticRandom.nextInt(n), (int) Math.round(Math.pow(this.getProblem().getV(), 1.0-this.getDepthOfSearch())), n)) {
            delta = ClauseUtils.calculateLoss(i, cba, constraints, influences[i]);
            improved = delta < 0;
            if (improved) {
                cba.swap(i);
                from.addConfictingClauses(delta);
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean usesDepthOfSearch() {
        return true;
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatHeuristicL2.class.getName());
    
}
