package parallelhyflex.utils;

import java.util.Arrays;

/**
 *
 * @author kommusoft
 */
public class CompactBitArray {
    
    private final long[] values;
    private int n;
    
    public CompactBitArray (int n) {
        this.n = n;
        this.values = new long[(n+63)>>6];
    }
    public CompactBitArray (long[] values) {
        this.n = 64*values.length;
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
        for(int i = fj+1; i < tj-1; i++) {
            values[i] = ~values[i];
        }
        long mask = (2L<<toIndex)-1;
        values[tj] ^= mask;
        mask = (2L<<fromIndex)-1;
        values[fj] ^= ~mask;
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
    
}
