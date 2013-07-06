package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentProblem extends ProblemBase<FrequencyAssignmentSolution, FrequencyAssignmentSolutionGenerator> {

    private final int nTransceivers;//TODO remove counters?
    private final int nSectors;
    private final int[][] frequencies;
    private final double[][] means;
    private final double[][] stdevs;
    private final int[] placement;

    public FrequencyAssignmentProblem(int nTransceivers, int nSectors, int[][] frequencies, double[][] means, double[][] stdevs, int[] placement) {
        this.nTransceivers = nTransceivers;
        this.nSectors = nSectors;
        this.frequencies = frequencies;
        this.means = means;
        this.stdevs = stdevs;
        this.placement = placement;
    }

    /**
     * @return the nTransceivers
     */
    public int getnTransceivers() {
        return nTransceivers;
    }

    /**
     * @return the nSectors
     */
    public int getnSectors() {
        return nSectors;
    }

    /**
     * @return the frequencies
     */
    public int[][] getFrequencies() {
        return frequencies;
    }

    /**
     * @return the means
     */
    public double[][] getMeans() {
        return means;
    }

    /**
     * @return the stdevs
     */
    public double[][] getStdevs() {
        return stdevs;
    }

    /**
     * @return the placement
     */
    public int[] getPlacement() {
        return placement;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(nTransceivers);
        dos.writeInt(nSectors);
        SerialisationUtils.writeIntArray2d(dos, frequencies);
        SerialisationUtils.writeDoubleArray2d(dos, means);
        SerialisationUtils.writeDoubleArray2d(dos, stdevs);
        SerialisationUtils.writeIntArray(dos, placement);
    }
    
}
