package parallelhyflex.problems.threesat;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.SerialisationUtils;
import parallelhyflex.problemdependent.ProblemReader;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemGenerator implements ProblemReader<ThreeSatSolution,ThreeSatProblem> {
    
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
            long index = ClauseUtils.getIndexI(fill,ci);
            fill = ClauseUtils.setValue(fill,ci,cba.getBit(index));
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

    @Override
    public ThreeSatProblem readAndGenerate(DataInputStream dis) throws IOException {
        long[] constraints = SerialisationUtils.readLongArray(dis);
        int[][] influences = SerialisationUtils.readIntArray2d(dis);
        int[][] blockInfluences = SerialisationUtils.readIntArray2d(dis);
        int[] vc = SerialisationUtils.readIntArray(dis);
        double[] stats = SerialisationUtils.readDoubleArray(dis);
        return new ThreeSatProblem(constraints,influences,blockInfluences,vc,stats);
    }
    
}
