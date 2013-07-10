package parallelhyflex.problems.threesat.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolution implements Solution<ThreeSatSolution> {

    private final CompactBitArray cba;
    private int conflictingClauses;

    ThreeSatSolution(int conflictingClauses, CompactBitArray cba) {
        this.conflictingClauses = conflictingClauses;
        this.cba = cba;
    }

    /**
     *
     * @param index
     * @return
     */
    public boolean get(int index) {
        return this.get(index);
    }

    /**
     *
     * @param index
     * @return
     */
    public long getBit(int index) {
        return this.getCompactBitArray().getBit(index);
    }

    /**
     *
     * @param index
     * @return
     */
    public long getBit(long index) {
        return this.getCompactBitArray().getBit(index);
    }

    /**
     *
     * @param constraint
     * @return
     */
    public boolean satisfiesClause(long constraint) {
        return this.getCompactBitArray().satisfiesClause(constraint);
    }

    /**
     *
     * @param index
     */
    public void swap(int index) {
        this.getCompactBitArray().swap(index);
    }

    /**
     *
     * @param fromIndex
     * @param toIndex
     */
    public void swapRange(int fromIndex, int toIndex) {
        this.getCompactBitArray().swapRange(fromIndex, toIndex);
    }

    /**
     *
     * @param fromIndex
     * @param toIndex
     */
    public void setRange(int fromIndex, int toIndex) {
        this.getCompactBitArray().setRange(fromIndex, toIndex);
    }

    /**
     *
     * @param fromIndex
     * @param toIndex
     */
    public void resetRange(int fromIndex, int toIndex) {
        this.getCompactBitArray().resetRange(fromIndex, toIndex);
    }

    /**
     *
     * @param index
     * @param value
     */
    public void set(int index, boolean value) {
        this.getCompactBitArray().set(index, value);
    }

    /**
     *
     * @return
     */
    public int getConflictingClauses() {
        return this.conflictingClauses;
    }

    void setConflictingClauses(int conflictingClauses) {
        this.conflictingClauses = conflictingClauses;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreeSatSolution) {
            return this.equalSolution((ThreeSatSolution) obj);
        }
        return false;
    }

    /**
     *
     * @param clauses
     */
    public void recalculateConflictingClauses(long[] clauses) {
        int nfail = 0;
        for (long clause : clauses) {
            if (!this.satisfiesClause(clause)) {
                nfail++;
            }
        }
        this.conflictingClauses = nfail;
    }

    /**
     *
     */
    public void clearTail() {
        this.getCompactBitArray().clearTail();
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return this.getCompactBitArray().hashCode();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.getCompactBitArray().toString();
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean hasFastDifferenceWith(ThreeSatSolution other) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public ThreeSatSolution clone() {
        return new ThreeSatSolution(this.getConflictingClauses(), getCompactBitArray().clone());
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean equalSolution(ThreeSatSolution other) {
        return (this.conflictingClauses == other.conflictingClauses && this.getCompactBitArray().equals(other.getCompactBitArray()));
    }

    /**
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream os) throws IOException {
        os.writeInt(this.getConflictingClauses());
        this.getCompactBitArray().writeSolution(os);
    }

    /**
     *
     * @param is
     * @throws IOException
     */
    @Override
    public void read(DataInputStream is) throws IOException {
        this.getCompactBitArray().readSolution(is);
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return this.getCompactBitArray().getLength();
    }

    /**
     * @return the cba
     */
    public CompactBitArray getCompactBitArray() {
        return cba;
    }

    /**
     *
     * @param delta
     */
    public void addConfictingClauses(int delta) {
        this.conflictingClauses += delta;
    }

    /**
     *
     * @param index
     * @param problem
     */
    public void swapBit(int index, ThreeSatProblem problem) {
        ClauseUtils.swapBit(index, problem.getInfluences()[index], cba, problem.getClauses(), this);
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatSolution.class.getName());
}
