package parallelhyflex.problems.threesat;

import parallelhyflex.ProblemPointerBase;
import parallelhyflex.problemdependent.EnforceableConstraint;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatEnforceableConstraint1 extends ProblemPointerBase<ThreeSatSolution,ThreeSatProblem> implements EnforceableConstraint<ThreeSatSolution> {

    private long data;
    
    public ThreeSatEnforceableConstraint1 (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void enforceTrue(ThreeSatSolution solution) {
        CompactBitArray cba = solution.getCompactBitArray();
        if(!cba.satisfiesClause(data)) {
            int ii = Utils.StaticRandom.nextInt(3);
            ClauseUtils.swapBit(ii, this.getProblem().getInfluences()[ii], cba, this.getProblem().getConstraints(), solution);
        }
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        CompactBitArray cba = solution.getCompactBitArray();
        if(cba.satisfiesClause(data)) {
            //TODO: set index
        }
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        return solution.satisfiesClause(data);
    }

    @Override
    public boolean isNotSatisfied(ThreeSatSolution solution) {
        return !solution.satisfiesClause(data);
    }
}
