package parallelhyflex.algebra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
}
