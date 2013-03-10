package parallelhyflex.problems.threesat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.Solution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolution implements Solution<ThreeSatSolution> {
    
    private final CompactBitArray cba;
    
    public ThreeSatSolution (int n) {
        this.cba = new CompactBitArray(n);
    }
    ThreeSatSolution (long[] values) {
        this.cba = new CompactBitArray(values);
    }
    ThreeSatSolution (CompactBitArray cba) {
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

    @Override
    public boolean equals (Object obj) {
        if(obj instanceof ThreeSatSolution) {
            return this.equalSolution((ThreeSatSolution) obj);
        }
        return false;
    }
    
    public static ThreeSatSolution randomInstance (int n) {
        return new ThreeSatSolution(CompactBitArray.randomInstance(n));
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
        return new ThreeSatSolution(cba.clone());
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
