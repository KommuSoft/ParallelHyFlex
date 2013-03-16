package parallelhyflex.problems.threesat;

import parallelhyflex.ProblemPointerBase;
import parallelhyflex.problemdependent.EnforcableConstraint;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatEnforcableConstraint1 extends ProblemPointerBase<ThreeSatSolution,ThreeSatProblem> implements EnforcableConstraint<ThreeSatSolution> {

    private long data;
    
    public ThreeSatEnforcableConstraint1 (ThreeSatProblem problem) {
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
