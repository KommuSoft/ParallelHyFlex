package parallelhyflex.problems.threesat;

import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

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
        CompactBitArray cba = CompactBitArray.randomInstance(this.n);
        for(int i = 0; i < k;) {
            long fill = (((long) Utils.StaticRandom.nextInt(8))<<60)|(((long) Utils.StaticRandom.nextInt(n))<<40)|(((long) Utils.StaticRandom.nextInt(n))<<20)|((long) Utils.StaticRandom.nextInt(n));
            int ci = Utils.StaticRandom.nextInt(3);
            long index = ((fill>>(20*ci))&0x0FFFFF);
            fill &= ~(0x1L<<(60+ci));
            fill |= cba.getBit(index)<<(60+ci);
            if(Utils.isValidClause(fill)) {
                constraints[i++] = fill;
            }
        }
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
