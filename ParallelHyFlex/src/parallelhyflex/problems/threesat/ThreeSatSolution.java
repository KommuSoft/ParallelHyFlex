package parallelhyflex.problems.threesat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.Communication;
import parallelhyflex.Solution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolution implements Solution<ThreeSatSolution> {
    
    private final long[] values;
    
    public ThreeSatSolution (int n64) {
        this.values = new long[n64];
    }
    ThreeSatSolution (long[] values) {
        this.values = values;
        Communication.Log(this.toString());
    }
    
    public boolean getValue (int index) {
        int j = index>>6;
        index -= j<<6;
        long mask = 1L<<index;
        return (values[j]&mask) != 0;
    }
    public void swapValue (int index) {
        int j = index>>6;
        index -= j<<6;
        long mask = 1L<<index;
        values[j] ^= mask;
    }
    public void setValue (int index, boolean value) {
        int j = index>>6;
        index -= j<<6;
        long mask = 1L<<index;
        values[j] &= ~mask;
        if(value) {
            values[j] |= mask;
        }
    }

    @Override
    public ThreeSatSolution clone() {
        int n64 = this.values.length;
        long[] data = new long[n64];
        System.arraycopy(this.values, 0, data, 0, n64);
        return new ThreeSatSolution(data);
    }

    @Override
    public boolean equalSolution(ThreeSatSolution other) {
        int n64 = this.values.length;
        if(n64 == other.values.length) {
            for(int i = 0; i < n64; i++) {
                if(this.values[i] != other.values[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void writeSolution(DataOutputStream os) throws IOException {
        for(int i = 0; i < values.length; i++) {
            os.writeLong(values[i]);
        }
    }

    @Override
    public void readSolution(DataInputStream is) throws IOException {
        for(int i = 0; i < values.length; i++) {
            values[i] = is.readLong();
        }
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
