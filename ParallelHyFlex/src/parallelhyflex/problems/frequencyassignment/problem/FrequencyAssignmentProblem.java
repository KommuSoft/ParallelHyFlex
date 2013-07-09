package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.algebra.generators.IntegerArrayIndexGenerator;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.interference.FunctionInterferenceStructure;
import parallelhyflex.interference.InterferenceStructure;
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
    private final DoubleUpperMatrix means;
    private final DoubleUpperMatrix stdevs;
    private final int[] placement;
    private FunctionInterferenceStructure<Integer, Integer> interference;

    public FrequencyAssignmentProblem(int nTransceivers, int nSectors, int[][] frequencies, DoubleUpperMatrix means, DoubleUpperMatrix stdevs, int[] placement) {
        this.nTransceivers = nTransceivers;
        this.nSectors = nSectors;
        this.frequencies = frequencies;
        this.means = means;
        this.stdevs = stdevs;
        this.placement = placement;
        this.interference = new FunctionInterferenceStructure<>(new IntegerArrayIndexGenerator(placement));
        this.setObjectives(new FrequencyAssignmentObjectiveFunction1());
        this.setSolutionGenerator(new FrequencyAssignmentSolutionGenerator(this));
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
    public DoubleUpperMatrix getMeans() {
        return means;
    }

    /**
     * @return the stdevs
     */
    public DoubleUpperMatrix getStdevs() {
        return stdevs;
    }

    /**
     * @return the placement
     */
    public int[] getPlacement() {
        return placement;
    }

    /**
     *
     * @param dos
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(nTransceivers);
        dos.writeInt(nSectors);
        SerialisationUtils.writeIntArray2d(dos, frequencies);
        means.write(dos);
        stdevs.write(dos);
        SerialisationUtils.writeIntArray(dos, placement);
    }

    /**
     *
     * @return
     */
    public InterferenceStructure<Integer> getInterferenceStructure() {
        return this.interference;
    }
}
