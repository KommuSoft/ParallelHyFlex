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
        long ia, ib, ic, i0, i1, i2;
        for(int i = 0; i < k;) {
            i0 = Utils.StaticRandom.nextInt(n);
            i1 = Utils.StaticRandom.nextInt(n);
            i2 = Utils.StaticRandom.nextInt(n);
            ia = Math.min(i0,Math.min(i1,i2));
            ic = Math.max(i0,Math.max(i1,i2));
            ib = i0+i1+i2-ia-ic;
            long fill = (((long) Utils.StaticRandom.nextInt(8))<<60)|(ia<<40)|(ib<<20)|ic;
            int ci = Utils.StaticRandom.nextInt(3);
            long index = ((fill>>(20*ci))&0x0FFFFF);
            fill &= ~(0x1L<<(60+ci));
            fill |= cba.getBit(index)<<(60+ci);
            if(ClauseUtils.isValidClause(fill)) {
                constraints[i++] = fill;
            }
        }
        return new ThreeSatProblem(constraints);
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
