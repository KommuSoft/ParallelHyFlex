package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public abstract class UpperMatrixBase<T> implements UpperMatrix<T> {

    private int n;
    
    public UpperMatrixBase (int n) {
        this.n = n;
    }
    
    @Override
    public T getA (int i, int j) {
        return this.get(i, j);
    }
    
    @Override
    public T getD (int i, int j) {
        return this.get(i, j);
    }
    
    public int getN () {
        return this.n;
    }
    
    protected void setN (int n) {
        this.n = n;
    }
    
    public int calculateOrderedIndex(int i, int j) {
        return i * (2 * n - i - 1) / 2 + j - i -1;
    }

    public int calculateSize(int n) {
        return Math.max(0x00,(n * n - n) / 2);
    }
    
}
