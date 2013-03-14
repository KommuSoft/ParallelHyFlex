package parallelhyflex.problems.threesat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.problemdependent.Solution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolution implements Solution<ThreeSatSolution> {
    
    private final CompactBitArray cba;
    private int conflictingClauses;
    
    ThreeSatSolution (int conflictingClauses, CompactBitArray cba) {
        this.conflictingClauses = conflictingClauses;
        this.cba = cba;
    }
    
    public boolean get (int index) {
        return this.get(index);
    }
    public long getBit (int index) {
        return this.getBit(index);
    }
    public long getBit (long index) {
        return this.getBit(index);
    }
    public boolean satisfiesClause (long constraint) {
        return this.cba.satisfiesClause(constraint);
    }
    public void swap (int index) {
        this.cba.swap(index);
    }
    public void swapRange (int fromIndex, int toIndex) {
        this.cba.swapRange(fromIndex, toIndex);
    }
    public void setRange (int fromIndex, int toIndex) {
        this.cba.setRange(fromIndex, toIndex);
    }
    public void resetRange (int fromIndex, int toIndex) {
        this.cba.resetRange(fromIndex, toIndex);
    }
    public void set (int index, boolean value) {
        this.cba.set(index, value);
    }
    public int getConflictingClauses () {
        return this.conflictingClauses;
    }
    void setConflictingClauses (int conflictingClauses) {
        this.conflictingClauses = conflictingClauses;
    }
    @Override
    public boolean equals (Object obj) {
        if(obj instanceof ThreeSatSolution) {
            return this.equalSolution((ThreeSatSolution) obj);
        }
        return false;
    }
    
    public void clearTail () {
        this.cba.clearTail();
    }

    @Override
    public int hashCode() {
        return this.cba.hashCode();
    }
    
    @Override
    public String toString () {
        return this.cba.toString();
    }
    
    @Override
    public boolean hasFastDifferenceWith (ThreeSatSolution other) {
        return false;
    }

    @Override
    public ThreeSatSolution clone() {
        return new ThreeSatSolution(this.getConflictingClauses(),cba.clone());
    }

    @Override
    public boolean equalSolution(ThreeSatSolution other) {
        return this.cba.equals(other.cba);
    }

    @Override
    public void writeSolution(DataOutputStream os) throws IOException {
        this.cba.writeSolution(os);
    }

    @Override
    public void readSolution(DataInputStream is) throws IOException {
        this.cba.readSolution(is);
    }
    
    public int getLength() {
        return this.cba.getLength();
    }
    
}
