package parallelhyflex.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class CompactBitArray implements ICompactBitArray, Collection<Integer> {

    public static final long BLOCK_MASK = 0xFFFF_FFFF_FFFF_FFBFL;

    public static CompactBitArray randomInstance(int n) {
        CompactBitArray cba = new CompactBitArray(n);
        for (int i = cba.values.length - 1; i >= 0; i--) {
            cba.values[i] = Utils.nextLong();
        }
        cba.clearTail();
        return cba;
    }

    public static CompactBitArray fromDataInputStream(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        int j = (n + 63) >> 6;
        long[] values = new long[j];
        for (int i = 0; i < j; i++) {
            values[i] = dis.readLong();
        }
        return new CompactBitArray(n, values);
    }
    public final long[] values;
    private int n;

    public CompactBitArray(int n) {
        this.n = n;
        this.values = new long[(n + 63) >> 6];
    }

    public CompactBitArray(long[] values) {
        this.n = 64 * values.length;
        this.values = values;
    }

    public CompactBitArray(int n, long[] values) {
        this.n = n;
        this.values = values;
    }

    public CompactBitArray(boolean[] values) {
        this(values.length);
        for (int i = 0, j = 0; i < values.length; j++) {
            long val = 0;
            int I = i + 64;
            for (; i < I; i++) {
                val <<= 1;
                if (values[i]) {
                    val |= 1;
                }
            }
            this.values[j] = val;
        }
    }

    @Override
    public boolean get(int index) {
        int j = index >> 6;
        index -= j << 6;
        long mask = 1L << index;
        return (values[j] & mask) != 0;
    }

    @Override
    public long getBit(int index) {
        int j = index >> 6;
        index -= j << 6;
        return (values[j] >> index) & 1;
    }

    @Override
    public long getBit(long index) {
        long j = index >> 6;
        index -= j << 6;
        return (values[(int) j] >> index) & 1;
    }

    @Override
    public boolean satisfiesClause(long constraint) {
        return (getBit((constraint >> 40) & 0xF_FFFF) == ((constraint >> 62) & 1)
                || getBit((constraint >> 20) & 0xF_FFFF) == ((constraint >> 61) & 1)
                || getBit(constraint & 0x0F_FFFF) == ((constraint >> 60) & 1));
    }

    public boolean satisfiesClauseWithout(long constraint, Collection<Integer> nonIndices) {
        int index1 = (int) (constraint >> 40) & 0xF_FFFF, index2 = (int) (constraint >> 20) & 0xF_FFFF, index3 = (int) (constraint & 0x0F_FFFF);
        return ((!nonIndices.contains(index1) && getBit(index1) == ((constraint >> 62) & 1))
                || (!nonIndices.contains(index2) && getBit(index2) == ((constraint >> 61) & 1))
                || (!nonIndices.contains(index3) && getBit(index3) == ((constraint >> 60) & 1)));
    }

    public boolean satisfiesClauseWithoutBlock(long constraint, int blockindex) {
        long index1 = (constraint >> 40) & 0xF_FFFF, index2 = (constraint >> 20) & 0xF_FFFF, index3 = constraint & 0x0F_FFFF;
        long pattern = (long) blockindex << 6;
        return (((index1 & BLOCK_MASK) != pattern && getBit(index1) == ((constraint >> 62) & 1))
                || ((index2 & BLOCK_MASK) != pattern && getBit(index2) == ((constraint >> 61) & 1))
                || ((index3 & BLOCK_MASK) != pattern && getBit(index3) == ((constraint >> 60) & 1)));
    }

    public int getNumberOfFailingClauses(long[] constraints) {
        int nfail = 0;
        for (long constraint : constraints) {
            if (!satisfiesClause(constraint)) {
                nfail++;
            }
        }
        return nfail;
    }

    @Override
    public void swap(int index) {
        int j = index >> 6;
        index -= j << 6;
        long mask = 1L << index;
        values[j] ^= mask;
    }

    @Override
    public void swapRange(int fromIndex, int toIndex) {
        int fj = fromIndex >> 6, tj = toIndex >> 6;
        if (fj != tj) {
            fromIndex -= fj << 6;
            toIndex -= tj << 6;
            for (int i = fj + 1; i < tj; i++) {
                values[i] = ~values[i];
            }
            long mask = (0x2L << toIndex) - 0x01;
            values[tj] ^= mask;
            mask = 0xFFFF_FFFF_FFFF_FFFFL << fromIndex;
            values[fj] ^= mask;
        } else {
            values[tj] ^= ((0x2L << toIndex) - 0x01) & (0xFFFF_FFFF_FFFF_FFFFL << fromIndex);
        }
    }

    @Override
    public void setRange(int fromIndex, int toIndex) {
        int fj = fromIndex >> 6, tj = toIndex >> 6;
        if (fj != tj) {
            fromIndex -= fj << 6;
            toIndex -= tj << 6;
            for (int i = fj + 1; i < tj; i++) {
                values[i] = 0xFFFF_FFFF_FFFF_FFFFL;
            }
            //System.out.println(toIndex);
            long mask = (0x2L << toIndex) - 0x01;
            //System.out.println(Utils.stringReverse(String.format("%64s", Long.toBinaryString(mask)).replace(' ', '0')));
            values[tj] |= mask;
            mask = 0xFFFF_FFFF_FFFF_FFFFL << fromIndex;
            values[fj] |= mask;
        } else {
            long mask = ((0x2L << toIndex) - 0x01) & (0xFFFF_FFFF_FFFF_FFFFL << fromIndex);
            values[fj] |= mask;
        }
    }

    @Override
    public void resetRange(int fromIndex, int toIndex) {
        int fj = fromIndex >> 6, tj = toIndex >> 6;
        if (fj != tj) {
            fromIndex -= fj << 6;
            toIndex -= tj << 6;
            for (int i = fj + 1; i < tj; i++) {
                values[i] = 0x0000_0000_0000_0000L;
            }
            long mask = (0x2L << toIndex) - 0x01;
            values[tj] &= ~mask;
            mask = 0xFFFF_FFFF_FFFF_FFFFL << fromIndex;
            values[fj] &= ~mask;
        } else {
            long mask = ((0x2L << toIndex) - 0x01) & (0xFFFF_FFFF_FFFF_FFFFL << fromIndex);
            values[tj] &= ~mask;
        }
    }

    @Override
    public void set(int index, boolean value) {
        int j = index >> 6;
        index -= j << 6;
        long mask = 1L << index;
        values[j] &= ~mask;
        if (value) {
            values[j] |= mask;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CompactBitArray) {
            CompactBitArray other = (CompactBitArray) obj;
            int n64 = this.values.length;
            if (n64 == other.values.length) {
                for (int i = 0; i < n64; i++) {
                    if (this.values[i] != other.values[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearTail() {
        long tailmask = 64 + this.n - (this.values.length << 6);
        //TODO: remove tail
        tailmask = (1L << tailmask) - 1;
        this.values[this.values.length - 1] &= tailmask;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Arrays.hashCode(this.values);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(0x41 * this.values.length - 0x01);
        for (int i = 0; i < this.values.length; i++) {
            sb.append(Utils.stringReverse(String.format("%64s", Long.toBinaryString(this.values[i])).replace(' ', '0'))).append(" ");
        }
        return sb.toString();
    }

    @Override
    public CompactBitArray clone() {
        long[] valc = new long[this.values.length];
        System.arraycopy(this.values, 0, valc, 0, valc.length);
        return new CompactBitArray(this.n, valc);
    }

    @Override
    public void writeSolution(DataOutputStream os) throws IOException {
        os.writeInt(this.n);
        for (int i = 0; i < values.length; i++) {
            os.writeLong(values[i]);
        }
    }

    @Override
    public void readSolution(DataInputStream is) throws IOException {
        this.n = is.readInt();
        for (int i = 0; i < values.length; i++) {
            values[i] = is.readLong();
        }
    }

    @Override
    public int getLength() {
        return this.n;
    }

    @Override
    public int getBlockLength() {
        return this.values.length;
    }

    @Override
    public boolean willSwap(long constraint, int index) {
        long index1 = (constraint >> 40) & 0xF_FFFF;
        long index2 = (constraint >> 20) & 0xF_FFFF;
        long index3 = constraint & 0xF_FFFF;
        return ((index1 == index || getBit(index1) != ((constraint >> 62) & 1))
                && (index2 == index || getBit(index2) != ((constraint >> 61) & 1))
                && (index3 == index || getBit(index3) != ((constraint >> 60) & 1)));
    }

    @Override
    public int swapGetBit(int index) {
        int j = index >> 6;
        index -= j << 6;
        long mask = 1L << index;
        values[j] ^= mask;
        return (int) ((values[j] >> index) & 0x01);
    }

    public void setAll(int[] indices, int val) {
        for (int i = 0; i < indices.length; i++) {
            this.set(indices[i], ((val >> i) & 0x01) != 0x00);
        }
    }

    public void andWith(CompactBitArray cba) {
        long[] valb = cba.values;
        for (int i = 0; i < values.length; i++) {
            values[i] &= valb[i];
        }
    }

    public void orWith(CompactBitArray cba) {
        long[] valb = cba.values;
        for (int i = 0; i < values.length; i++) {
            values[i] |= valb[i];
        }
    }

    public void xorWith(CompactBitArray cba) {
        long[] valb = cba.values;
        for (int i = 0; i < values.length; i++) {
            values[i] ^= valb[i];
        }
    }

    public void swapFull() {
        for (int i = 0; i < values.length; i++) {
            values[i] = ~values[i];
        }
        this.clearTail();
    }

    @Override
    public int size() {
        int size = 0x00;
        long[] vals = this.values;
        int nVals = vals.length;
        for (int i = 0x00; i < nVals; i++) {
            size += Utils.countOnes(vals[i]);
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        long[] vals = this.values;
        int nVals = vals.length;
        for (int i = 0x00; i < nVals; i++) {
            if (vals[i] != 0x00) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Integer) {
            return this.get((Integer) o);
        } else {
            return false;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        int size = this.size();
        Object[] result = new Object[size];
        long[] values = this.values;
        int n = values.length, k = 0x00, l = 0x00;
        for(int i = 0x00; i < n; i++) {
            long dat = values[i];
            l = i<<0x06;
            while(dat != 0x00) {
                if((dat&0x01) != 0x00) {
                    result[k++] = l;
                }
                l++;
                dat >>>= 0x01;
            }
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Integer e) {
        boolean result = !this.get(e);
        this.set(e, true);
        return result;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Integer) {
            int index = (Integer) o;
            boolean result = this.get(index);
            this.set(index, false);
            return result;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object o : clctn) {
            if (!(o instanceof Integer) || !this.get((Integer) o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> clctn) {
        boolean result = false;
        for(Integer i : clctn) {
            result |= this.add(i);
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        boolean result = false;
        for(Object o : clctn) {
            result |= clctn.remove(o);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        long[] values = this.values;
        int n = this.values.length;
        for(int i = 0x00; i < n; i++) {
            values[i] = 0x00;
        }
    }
}
