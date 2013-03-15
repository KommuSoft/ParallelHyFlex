package parallelhyflex.problems.threesat;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.HeuristicBase;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM2 extends HeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatHeuristicM2 (ThreeSatProblem problem) {
        super(problem,HeuristicType.Mutation);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        long[] constraint = this.getProblem().getConstraints();
        CompactBitArray cba = from.getCompactBitArray();
        int ic = Utils.StaticRandom.nextInt(constraint.length);
        int ri = Utils.StaticRandom.nextInt(3);
        //TODO: search for other clause (if fails)
    }
    
}
