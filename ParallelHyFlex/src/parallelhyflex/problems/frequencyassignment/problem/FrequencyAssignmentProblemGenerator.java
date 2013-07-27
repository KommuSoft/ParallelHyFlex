package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;
import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentProblemGenerator implements ProblemReader<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public FrequencyAssignmentProblem readAndGenerate(DataInputStream dis) throws IOException {
        int nTransceivers = dis.readInt();
        int nSectors = dis.readInt();
        double k = dis.readDouble();
        double csh = dis.readDouble();
        double cacr = dis.readDouble();
        int[][] frequencies = SerialisationUtils.readIntArray2d(dis);
        DoubleUpperMatrix means = new DoubleUpperMatrix(0x00);
        means.read(dis);
        DoubleUpperMatrix stdevs = new DoubleUpperMatrix(0x00);
        stdevs.read(dis);
        int[] placement = SerialisationUtils.readIntArray(dis);
        return new FrequencyAssignmentProblem(nTransceivers, nSectors, frequencies, means, stdevs, placement, k, csh, cacr);
    }

    /**
     *
     * @return
     */
    public FrequencyAssignmentProblem generateProblem() {
        int n = Math.max(0x02, Utils.nextInt(8_000));
        int m = Math.max(0x02, Utils.nextInt((int) Math.ceil(Math.sqrt(n))));
        int l = Math.max(0x02, Utils.nextInt((int) Math.ceil(0.5d * Math.sqrt(n))));
        int[][] freqs = new int[n][];
        int[] placement = new int[n];
        for (int i = 0x00; i < n; i++) {
            int k = Math.max(0x01, Utils.nextInt((int) Math.ceil(2.0d * Math.sqrt(m))));
            int[] vals = new int[k];
            for (int j = 0x00; j < k; j++) {
                vals[j] = Utils.nextInt(l);
            }
            freqs[i] = vals;
            placement[i] = Utils.nextInt(m);
        }
        DoubleUpperMatrix means = DoubleUpperMatrix.generateRandomGaussian(m, 0.0d, 1.0d);
        DoubleUpperMatrix sigmas = DoubleUpperMatrix.generateRandomAbsoluteGaussian(m, 1.0d, 1.0d);
        double k = Math.pow(1.0d, 3.0d * Utils.nextDouble() + 4.0d);
        double csh = Math.pow(1.0d, -2.0d * Utils.nextDouble());
        double cacr = Math.pow(1.0d, -2.0d * Utils.nextDouble());
        return new FrequencyAssignmentProblem(n, m, freqs, means, sigmas, placement, k, csh, cacr);
    }

    public FrequencyAssignmentProblem readFromFile(String text) {
        Scanner sc = new Scanner(text);
        int n = sc.nextInt();//nTranceivers
        int m = sc.nextInt();//sectors
        double k = sc.nextDouble();//k
        double csh = sc.nextDouble();//csh
        double cacr = sc.nextDouble();//cacr
        int[][] frequencies = new int[n][];
        for (int i = 0x00; i < n; i++) {
            int l = sc.nextInt();
            int[] freq = new int[l];
            for (int j = 0x00; j < l; j++) {
                freq[j] = sc.nextInt();
            }
            frequencies[i] = freq;
        }
        int[] placement = new int[n];
        for (int i = 0x00; i < n; i++) {
            placement[i] = sc.nextInt();
        }
        DoubleUpperMatrix means = new DoubleUpperMatrix(m);
        DoubleUpperMatrix stdevs = new DoubleUpperMatrix(m);
        for (int i = 0x00; i < m; i++) {
            for (int j = i + 0x01; j < m; j++) {
                means.set(i, j, sc.nextDouble());
                stdevs.set(i, j, sc.nextDouble());
            }
        }
        return new FrequencyAssignmentProblem(n, m, frequencies, means, stdevs, placement, k, csh, cacr);
    }
}
