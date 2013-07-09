package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public abstract class UpperMatrixBase<T> implements UpperMatrix<T> {

    private int n;
    
    /**
     *
     * @param n
     */
    public UpperMatrixBase (int n) {
        this.n = n;
    }
    
    /**
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public T getA (int i, int j) {
        return this.get(i, j);
    }
    
    /**
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public T getD (int i, int j) {
        return this.get(i, j);
    }
    
    /**
     *
     * @return
     */
    public int getN () {
        return this.n;
    }
    
    /**
     *
     * @param n
     */
    protected void setN (int n) {
        this.n = n;
    }
    
    /**
     *
     * @param i
     * @param j
     * @return
     */
    public int calculateOrderedIndex(int i, int j) {
        return i * (2 * n - i - 1) / 2 + j - i -1;
    }

    /**
     *
     * @param n
     * @return
     */
    public int calculateSize(int n) {
        return Math.max(0x00,(n * n - n) / 2);
    }
    
}
