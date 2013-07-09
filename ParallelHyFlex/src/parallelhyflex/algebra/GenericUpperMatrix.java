package parallelhyflex.algebra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 * @author kommusoft
 */
public class GenericUpperMatrix<T> extends UpperMatrixBase<T> {
    
    private Object[] data;
    
    /**
     *
     * @param n
     * @param data
     */
    public GenericUpperMatrix(int n, T... data) {
        super(n);
        int size = calculateSize(n);
        this.data = new Object[size];
        System.arraycopy(data, 0, this.data, 0, Math.min(size, data.length));
    }
    
    /**
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int i, int j) {
        int n = this.getN();
        if (i < j && j < n) {
            return (T) this.data[calculateOrderedIndex(i, j)];
        } else if (i > j && i < n) {
            return (T) this.data[calculateOrderedIndex(j, i)];
        } else {
            return null;
        }
    }
    
    /**
     *
     * @param dis
     * @throws IOException
     */
    @Override
    public void read(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        this.setN(n);
        int N = calculateSize(n);
        this.data = new Object[N];
        try (ObjectInputStream ois = new ObjectInputStream(dis)) {
            for (int i = 0x00; i < N; i++) {
                try {
                    this.data[i] = ois.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GenericUpperMatrix.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     *
     * @param dos
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream dos) throws IOException {
        int n = this.getN();
        dos.writeInt(n);
        try (ObjectOutputStream ods = new ObjectOutputStream(dos)) {
            for (int i = 0x00; i < this.data.length; i++) {
                ods.writeObject(this.data[i]);
            }
        }
    }
    
    /**
     *
     * @param i
     * @param j
     * @param value
     */
    @Override
    public void set(int i, int j, T value) {
        int n = this.getN();
        if (i < j && j < n) {
            this.data[calculateOrderedIndex(i, j)] = value;
        } else if (i > j && i < n) {
            this.data[calculateOrderedIndex(j, i)] = value;
        }
    }
}
