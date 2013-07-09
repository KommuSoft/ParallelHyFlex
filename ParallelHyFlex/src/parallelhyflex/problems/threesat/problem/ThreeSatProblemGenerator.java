package parallelhyflex.problems.threesat.problem;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemGenerator implements ProblemReader<ThreeSatSolution, ThreeSatProblem> {

    private int n, k;

    /**
     *
     * @param n
     * @param k
     */
    public ThreeSatProblemGenerator(int n, int k) {
        this.n = n;
        this.k = k;
    }

    /**
     *
     * @return
     */
    public ThreeSatProblem generateProblem() {
        long[] constraints = new long[k];
        CompactBitArray cba = CompactBitArray.randomInstance(this.n);
        for (int i = 0; i < k;) {
            long fill = ClauseUtils.generateTrueClause(cba);
            if (ClauseUtils.isValidClause(fill)) {
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

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public ThreeSatProblem readAndGenerate(DataInputStream dis) throws IOException {
        long[] constraints = SerialisationUtils.readLongArray(dis);
        int[][] influences = SerialisationUtils.readIntArray2d(dis);
        int[][] blockInfluences = SerialisationUtils.readIntArray2d(dis);
        double[] indexCDF = SerialisationUtils.readDoubleArray(dis);
        int[] vc = SerialisationUtils.readIntArray(dis);
        double[] stats = SerialisationUtils.readDoubleArray(dis);
        return new ThreeSatProblem(constraints, influences, blockInfluences, indexCDF, vc, stats);
    }
}
