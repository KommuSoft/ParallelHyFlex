package parallelhyflex.problems.threesat;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.problemdependent.WritableEnforceableConstraintBase;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint1 extends WritableEnforceableConstraintBase<ThreeSatSolution,ThreeSatProblem> {

    private long data;
    public static final long MASK_BIT = 0x8000000000000000L;
    public static final long MASK = 0x7FFFFFFFFFFFFFFFL;
    
    public ThreeSatWritableEnforceableConstraint1 (ThreeSatProblem problem) {
        super(problem);
    }

    ThreeSatWritableEnforceableConstraint1(ThreeSatProblem problem, long data) {
        this(problem);
        this.data = data;
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
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong(MASK_BIT|this.data);
    }
}
