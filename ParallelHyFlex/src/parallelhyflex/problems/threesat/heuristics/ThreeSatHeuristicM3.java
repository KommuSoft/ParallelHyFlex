package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM3 extends MutationHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicM3(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        long[] constraints = this.getProblem().getConstraints();
        int[][] influences = this.getProblem().getInfluences();
        CompactBitArray cba = from.getCompactBitArray();
        long constraint = constraints[Utils.StaticRandom.nextInt(constraints.length)];
        if (!cba.satisfiesClause(constraint)) {
            int ii = ClauseUtils.getIndexI(constraint, Utils.StaticRandom.nextInt(3));
            ClauseUtils.swapBit(ii, influences[ii], cba, constraints, from);
        }
        //TODO: search for other clause (if fails)
    }
}
