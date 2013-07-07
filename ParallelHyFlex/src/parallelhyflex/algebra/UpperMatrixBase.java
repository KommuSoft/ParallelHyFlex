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
    
    public int getN () {
        return this.n;
    }
    
    protected void setN (int n) {
        this.n = n;
    }
    
    protected int calculateOrderedIndex(int i, int j) {
        return i * (2 * n - i - 3) / 2 + j - i - 1;
    }

    protected int calculateSize(int n) {
        return Math.max(0x00,(n * n - 3 * n + 2) / 2);
    }
    
}
