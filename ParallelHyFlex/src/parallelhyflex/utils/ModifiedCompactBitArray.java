package parallelhyflex.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author kommusoft
 */
public class ModifiedCompactBitArray implements ICompactBitArray {

    private final CompactBitArray innercba;
    private final HashMap<Integer, Long> differenceSet = new HashMap<>();

    public ModifiedCompactBitArray(CompactBitArray innercba) {
        this.innercba = innercba;
    }

    @Override
    public void clearTail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean get(int index) {
        int j = index >> 6;
        index -= j << 6;
        long mask = 1L << index;
        long value;
        if (!differenceSet.containsKey(j)) {
            value = this.innercba.values[j];
        } else {
            value = differenceSet.get(j);
        }
        return (value & mask) != 0;
    }

    @Override
    public long getBit(int index) {
        int j = index >> 6;
        index -= j << 6;
        long value;
        if (!differenceSet.containsKey(j)) {
            value = this.innercba.values[j];
        } else {
            value = differenceSet.get(j);
        }
        return (value >> index) & 1;
    }

    @Override
    public long getBit(long index) {
        int j = (int) (index >> 6);
        index -= j << 6;
        long value;
        if (!differenceSet.containsKey(j)) {
            value = this.innercba.values[j];
        } else {
            value = differenceSet.get(j);
        }
        return (value >> index) & 1;
    }

    @Override
    public void readSolution(DataInputStream is) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resetRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean satisfiesClause(long constraint) {
        return (getBit(constraint & 0x0F_FFFF) == ((constraint >> 60) & 1)
                || getBit((constraint >> 20) & 0xF_FFFF) == ((constraint >> 61) & 1)
                || getBit((constraint >> 40) & 0xF_FFFF) == ((constraint >> 62) & 1));
    }

    @Override
    public void set(int index, boolean value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void swap(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void swapRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeSolution(DataOutputStream os) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getLength() {
        return this.innercba.getLength();
    }

    @Override
    public int getBlockLength() {
        return this.innercba.getBlockLength();
    }

    @Override
    public boolean willSwap(long constraint, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int swapGetBit(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
