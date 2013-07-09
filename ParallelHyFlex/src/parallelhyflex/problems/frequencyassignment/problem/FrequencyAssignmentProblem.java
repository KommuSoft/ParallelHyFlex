package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.algebra.generators.IntegerArrayIndexGenerator;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.interference.FunctionInterferenceStructure;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicC1;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicC2;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicM1;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicM2;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;
import parallelhyflex.utils.Utils;

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
        this.setHeuristics(new FrequencyAssignmentHeuristicC1(this), new FrequencyAssignmentHeuristicC2(this), new FrequencyAssignmentHeuristicM1(this), new FrequencyAssignmentHeuristicM2(this));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.nTransceivers;
        hash = 37 * hash + this.nSectors;
        hash = 37 * hash + Arrays.deepHashCode(this.frequencies);
        hash = 37 * hash + Objects.hashCode(this.means);
        hash = 37 * hash + Objects.hashCode(this.stdevs);
        hash = 37 * hash + Arrays.hashCode(this.placement);
        hash = 37 * hash + Objects.hashCode(this.interference);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FrequencyAssignmentProblem)) {
            return false;
        }
        FrequencyAssignmentProblem fap = (FrequencyAssignmentProblem) obj;
        return (this.nSectors == fap.nSectors && this.nTransceivers == fap.nTransceivers && this.means.equals(fap.means) && this.stdevs.equals(fap.stdevs) && Utils.arrayEquality(placement, fap.placement) && Utils.arrayEquality(this.frequencies, fap.frequencies));
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

    @Override
    public String toString() {
        return "FrequencyAssignmentProblem{" + "nTransceivers=" + nTransceivers + ", nSectors=" + nSectors + ", frequencies=" + frequencies + ", means=\n" + means + ", stdevs=\n" + stdevs + ", placement=" + placement + ", interference=" + interference + '}';
    }
}
