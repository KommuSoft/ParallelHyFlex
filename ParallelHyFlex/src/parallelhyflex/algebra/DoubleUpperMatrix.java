package parallelhyflex.algebra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class DoubleUpperMatrix extends UpperMatrixBase<Double> {

    public static DoubleUpperMatrix generateRandomGaussian(int n, double mean, double sigma) {
        int m = n * (n - 1) / 2;
        double[] data = new double[m];
        for (int i = 0x00; i < m; i++) {
            data[i] = sigma * Utils.nextGaussian() + mean;
        }
        return new DoubleUpperMatrix(n, data);
    }

    public static DoubleUpperMatrix generateRandomAbsoluteGaussian(int n, double mean, double sigma) {
        int m = n * (n - 1) / 2;
        double[] data = new double[m];
        for (int i = 0x00; i < m; i++) {
            data[i] = Math.abs(sigma * Utils.nextGaussian() + mean);
        }
        return new DoubleUpperMatrix(n, data);
    }
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
    public Double getA(int i, int j) {
        int n = this.getN();
        if (j < n) {
            return this.data[calculateOrderedIndex(i, j)];
        } else {
            return Double.NaN;
        }
    }

    @Override
    public Double getD(int i, int j) {
        int n = this.getN();
        if (i < n) {
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
        double[] data = new double[size];
        for (int i = 0x00; i < size; i++) {
            data[i] = dis.readDouble();
        }
        this.data = data;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.getN());
        final double[] data = this.data;
        for (int i = 0x00; i < data.length; i++) {
            dos.writeDouble(data[i]);
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

    @Override
    public String toString() {
        int n = this.getN();
        StringBuilder sb = new StringBuilder();
        int k = 0x00;
        for (int i = 0x00; i < n; i++) {
            for(int j = 0x00; j <= i; j++) {
                sb.append("\t");
            }
            for (int j = i+0x01; j < n; j++) {
                sb.append(String.format("%.2f\t", this.data[k++]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
