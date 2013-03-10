package parallelhyflex.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author kommusoft
 */
public class CompactBitArray implements ICompactBitArray {
    
    final long[] values;
    private int n;
    
    public CompactBitArray (int n) {
        this.n = n;
        this.values = new long[(n+63)>>6];
    }
    public CompactBitArray (long[] values) {
        this.n = 64*values.length;
        this.values = values;
    }
    public CompactBitArray (int n, long[] values) {
        this.n = n;
        this.values = values;
    }
    public CompactBitArray (boolean[] values) {
        this(values.length);
        for(int i = 0, j = 0; i < values.length; j++) {
            long val = 0;
            int I = i+64;
            for(; i < I; i++) {
                val <<= 1;
                if(values[i]) {
                    val |= 1;
                }
            }
            this.values[j] = val;
        }
    }
    
    public boolean get (int index) {
        int j = index>>6;
        index -= j<<6;
        long mask = 1L<<index;
        return (values[j]&mask) != 0;
    }
    public long getBit (int index) {
        int j = index>>6;
        index -= j<<6;
        return (values[j]>>index)&1;
    }
    public long getBit (long index) {
        long j = index>>6;
        index -= j<<6;
        return (values[(int) j]>>index)&1;
    }
    public boolean satisfiesClause (long constraint) {
        return (getBit(constraint&0x0FFFFF) == ((constraint>>60)&1) ||
                getBit((constraint>>20)&0xFFFFF) == ((constraint>>61)&1) ||
                getBit((constraint>>40)&0xFFFFF) == ((constraint>>62)&1));
    }
    public void swap (int index) {
        int j = index>>6;
        index -= j<<6;
        long mask = 1L<<index;
        values[j] ^= mask;
    }
    public void swapRange (int fromIndex, int toIndex) {
        int fj = fromIndex>>6, tj = toIndex>>6;
        fromIndex -= fj<<6;
        toIndex -= tj<<6;
        for(int i = fj+1; i < tj; i++) {
            values[i] = ~values[i];
        }
        long mask = (4L<<toIndex)-1;
        values[tj] ^= mask;
        mask = (2L<<fromIndex)-1;
        values[fj] ^= ~mask;
        //TODO: same master
    }
    public void setRange (int fromIndex, int toIndex) {
        int fj = fromIndex>>6, tj = toIndex>>6;
        fromIndex -= fj<<6;
        toIndex -= tj<<6;
        for(int i = fj+1; i < tj; i++) {
            values[i] = 0xFFFFFFFFFFFFFFFFL;
        }
        long mask = (4L<<toIndex)-1;
        values[tj] |= mask;
        mask = (2L<<fromIndex)-1;
        values[fj] |= ~mask;
        //TODO: same master
    }
    public void resetRange (int fromIndex, int toIndex) {
        int fj = fromIndex>>6, tj = toIndex>>6;
        fromIndex -= fj<<6;
        toIndex -= tj<<6;
        for(int i = fj+1; i < tj; i++) {
            values[i] = 0x0000000000000000L;
        }
        long mask = (4L<<toIndex)-1;
        values[tj] &= ~mask;
        mask = (2L<<fromIndex)-1;
        values[fj] &= mask;
        //TODO: same master
    }
    public void set (int index, boolean value) {
        int j = index>>6;
        index -= j<<6;
        long mask = 1L<<index;
        values[j] &= ~mask;
        if(value) {
            values[j] |= mask;
        }
    }

    @Override
    public boolean equals (Object obj) {
        if(obj instanceof CompactBitArray) {
            CompactBitArray other = (CompactBitArray) obj;
            int n64 = this.values.length;
            if(n64 == other.values.length) {
                for(int i = 0; i < n64; i++) {
                    if(this.values[i] != other.values[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public static CompactBitArray randomInstance (int n) {
        CompactBitArray cba = new CompactBitArray(n);
        for(int i = cba.values.length-1; i >= 0; i--) {
            cba.values[i] = Utils.StaticRandom.nextLong();
        }
        cba.clearTail();
        return cba;
    }
    
    public void clearTail () {
        long tailmask = 64+this.n-(this.values.length<<6);
        //TODO: remove tail
        tailmask = (1L<<tailmask)-1;
        this.values[this.values.length-1] &= tailmask;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Arrays.hashCode(this.values);
        return hash;
    }
    
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.values.length; i++) {
            sb.append(String.format("%s ",Utils.stringReverse(String.format("%64s", Long.toBinaryString(this.values[i])).replace(' ', '0'))));
        }
        return sb.toString();
    }
    
    public CompactBitArray clone () {
        long[] valc = new long[this.values.length];
        System.arraycopy(this.values, 0, valc, 0, valc.length);
        return new CompactBitArray(this.n,valc);
    }

    public void writeSolution(DataOutputStream os) throws IOException {
        os.writeInt(this.n);
        for(int i = 0; i < values.length; i++) {
            os.writeLong(values[i]);
        }
    }
    public void readSolution(DataInputStream is) throws IOException {
        this.n = is.readInt();
        for(int i = 0; i < values.length; i++) {
            values[i] = is.readLong();
        }
    }
    
}