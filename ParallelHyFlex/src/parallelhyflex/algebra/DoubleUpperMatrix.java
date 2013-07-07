package parallelhyflex.algebra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author kommusoft
 */
public class DoubleUpperMatrix extends UpperMatrixBase<Double> {

    private double[] data;

    public DoubleUpperMatrix(int n, double... data) {
        super(n);
        int size = this.calculateSize(n);
        this.data = new double[size];
        System.arraycopy(data, 0x00, this.data, 0x00, Math.min(size, data.length));
    }

    @Override
    public Double get(int i, int j) {
        int n = this.getN();
        if (i < j && j < n) {
            return this.data[calculateOrderedIndex(i, j)];
        } else if (i > j && i < n) {
            return this.data[calculateOrderedIndex(j, i)];
        } else {
            return Double.NaN;
        }
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        this.setN(n);
        int size = calculateSize(n);
        this.data = new double[size];
        for (int i = 0x00; i < size; i++) {
            this.data[i] = dis.readDouble();
        }
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.getN());
        for (int i = 0x00; i < this.data.length; i++) {
            dos.writeDouble(this.data[i]);
        }
    }

    @Override
    public void set(int i, int j, Double value) {
        int n = this.getN();
        if (i < j && j < n) {
            this.data[calculateOrderedIndex(i, j)] = value;
        } else if (i > j && i < n) {
            this.data[calculateOrderedIndex(j, i)] = value;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.getN();
        hash = 97 * hash + Arrays.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DoubleUpperMatrix other = (DoubleUpperMatrix) obj;
        if (!Arrays.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
}
