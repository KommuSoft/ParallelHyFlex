package parallelhyflex.problems.threesat;

import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemGenerator {
    
    private int n, k;
    
    public ThreeSatProblemGenerator (int n, int k) {
        this.n = n;
        this.k = k;
    }
    
    public ThreeSatProblem generateProblem () {
        long[] constraints = new long[k];
        CompactBitArray cba = new CompactBitArray.randomInstance(this.n);
        return new ThreeSatProblem(this.n,constraints);
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(int k) {
        this.k = k;
    }
    
}
