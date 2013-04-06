package parallelhyflex.problems.threesat.constraints;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint1 extends ThreeSatWritableEnforceableConstraint {

    private long constraint;
    public static final long MASK_BIT = 0x8000000000000000L;
    public static final long MASK = 0x7FFFFFFFFFFFFFFFL;

    public ThreeSatWritableEnforceableConstraint1(ThreeSatProblem problem, long constraint) {
        super(problem);
        this.constraint = constraint;
    }

    @Override
    public void enforceTrue(ThreeSatSolution solution) {
        CompactBitArray cba = solution.getCompactBitArray();
        if (!cba.satisfiesClause(constraint)) {
            int ii = Utils.StaticRandom.nextInt(3);
            solution.swapBit(ClauseUtils.getIndexI(this.getConstraint(), ii), this.getProblem());
        }
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        CompactBitArray cba = solution.getCompactBitArray();
        ThreeSatProblem tsp = this.getProblem();
        if (cba.satisfiesClause(getConstraint())) {
            int index = ClauseUtils.getIndex0(this.constraint);
            if (cba.getBit(index) == ClauseUtils.getValue0(this.constraint)) {
                solution.swapBit(index, tsp);
            }
            index = ClauseUtils.getIndex1(this.constraint);
            if (cba.getBit(index) == ClauseUtils.getValue1(this.constraint)) {
                solution.swapBit(index, tsp);
            }
            index = ClauseUtils.getIndex2(this.constraint);
            if (cba.getBit(index) == ClauseUtils.getValue2(this.constraint)) {
                solution.swapBit(index, tsp);
            }
        }
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        return solution.satisfiesClause(getConstraint());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong(MASK_BIT | this.getConstraint());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreeSatWritableEnforceableConstraint1) {
            return this.getConstraint() == ((ThreeSatWritableEnforceableConstraint1) obj).getConstraint();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.getConstraint() ^ (this.getConstraint() >>> 32));
        return hash;
    }

    /**
     * @return the data
     */
    public long getConstraint() {
        return constraint;
    }

    /**
     * @param data the data to set
     */
    public void setData(long data) {
        this.constraint = data;
    }
}
