package parallelhyflex.problems.threesat.problem;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;
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

    public ThreeSatProblem readFromStream(InputStream is) {
        long[] constraints;
        try (Scanner sc = new Scanner(is)) {
            String line = "";
            while(line != null && !line.startsWith("p")) {
                line = sc.nextLine();
            }
            int nClauses = Integer.parseInt(line.replaceAll(" +"," ").split(" ")[3]);
            constraints = new long[nClauses];
            int[] clause = new int[3];
            boolean[] clausb = new boolean[3];
            for (int i = 0; i < nClauses; i++) {
                int k = sc.nextInt();
                int l = 0;
                while (k != 0) {
                    clausb[l] = k > 0;
                    clause[l] = Math.abs(k) - 1;
                    l++;
                    k = sc.nextInt();
                }
                sc.nextLine();
                l--;
                for (int j = l; j < 3; j++) {
                    clause[j] = clause[l];
                    clausb[j] = clausb[l];
                }
                constraints[i] = ClauseUtils.generateClause(clause[0], clause[1], clause[2], clausb[0], clausb[1], clausb[2]);
            }
        }
        return new ThreeSatProblem(constraints);
    }

    /**
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ThreeSatProblem readFromFile(String filename) throws FileNotFoundException, IOException {
        ThreeSatProblem tsp;
        try (FileInputStream fis = new FileInputStream(filename)) {
            tsp = this.readFromStream(fis);
        }
        return tsp;
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
    private static final Logger LOG = Logger.getLogger(ThreeSatProblemGenerator.class.getName());
}
