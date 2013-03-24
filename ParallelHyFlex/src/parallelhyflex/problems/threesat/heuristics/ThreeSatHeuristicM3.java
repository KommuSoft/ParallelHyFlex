package parallelhyflex.problems.threesat.heuristics;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.HeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.ThreeSatProblem;
import parallelhyflex.problems.threesat.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM3 extends HeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatHeuristicM3 (ThreeSatProblem problem) {
        super(problem,HeuristicType.Mutation);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        long[] constraints = this.getProblem().getConstraints();
        int[][] influences = this.getProblem().getInfluences();
        CompactBitArray cba = from.getCompactBitArray();
        long constraint = constraints[Utils.StaticRandom.nextInt(constraints.length)];
        if(!cba.satisfiesClause(constraint)) {
            int ii = ClauseUtils.getIndexI(constraint,Utils.StaticRandom.nextInt(3));
            ClauseUtils.swapBit(ii, influences[ii], cba, constraints, from);
        }
        //TODO: search for other clause (if fails)
    }
    
}
