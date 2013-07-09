package parallelhyflex.problems.frequencyassignment.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentUtils;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentObjectiveFunction1;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentSolution implements Solution<FrequencyAssignmentSolution> {

    private final int[] frequencyAssignment;
    private double interference;
    private int nConflicts;

    public FrequencyAssignmentSolution(double interference, int nConflicts, int[] frequencyAssignment) {
        this.frequencyAssignment = frequencyAssignment;
        this.interference = interference;
        this.nConflicts = nConflicts;
    }

    /**
     *
     * @param frequencyAssignment
     * @param problem
     */
    public FrequencyAssignmentSolution(int[] frequencyAssignment, FrequencyAssignmentProblem problem) {
        this.frequencyAssignment = frequencyAssignment;
        this.interference = FrequencyAssignmentUtils.calculateInterference(problem, frequencyAssignment);
        this.nConflicts = FrequencyAssignmentUtils.calculateNConflicts(problem, frequencyAssignment);
    }

    /**
     *
     * @param frequencyAssignment
     * @param evaluation
     * @param nConflicts
     */
    public FrequencyAssignmentSolution(int[] frequencyAssignment, double evaluation, int nConflicts) {
        this(evaluation, nConflicts, frequencyAssignment);
    }

    /**
     *
     * @return
     */
    @Override
    public FrequencyAssignmentSolution clone() {
        int[] data = new int[this.getFrequencyAssignment().length];
        System.arraycopy(this.getFrequencyAssignment(), 0, data, 0, data.length);
        return new FrequencyAssignmentSolution(data, this.getInterference(), this.getnConflicts());
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean equalSolution(FrequencyAssignmentSolution other) {
        return (this.getnConflicts() == other.getnConflicts() && Utils.isEqualTolerance(this.getInterference(), other.getInterference()) && Utils.arrayEquality(getFrequencyAssignment(), other.getFrequencyAssignment()));
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean hasFastDifferenceWith(FrequencyAssignmentSolution other) {
        return false;
    }

    /**
     *
     * @param dis
     * @throws IOException
     */
    @Override
    public void read(DataInputStream dis) throws IOException {
        this.setInterference(dis.readDouble());
        this.setnConflicts(dis.readInt());
        SerialisationUtils.readIntArray(dis, getFrequencyAssignment());
    }

    /**
     *
     * @param dos
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeDouble(this.getInterference());
        dos.writeInt(this.getnConflicts());
        SerialisationUtils.writeIntArray(dos, getFrequencyAssignment());
    }

    /**
     * @return the frequencyAssignment
     */
    public int[] getFrequencyAssignment() {
        return frequencyAssignment;
    }

    /**
     * @return the evaluation
     */
    public double getInterference() {
        return interference;
    }

    /**
     *
     * @param dInterference
     */
    public void interferenceDelta(double dInterference) {
        this.interference += dInterference;
    }

    /**
     *
     * @param tsp
     * @return
     */
    public double calculateInterference(FrequencyAssignmentProblem tsp) {
        return FrequencyAssignmentUtils.calculateInterference(tsp, this.frequencyAssignment);
    }

    /**
     *
     * @param tsp
     * @return
     */
    public double calculateNConflicts(FrequencyAssignmentProblem tsp) {
        return FrequencyAssignmentUtils.calculateNConflicts(tsp, this.frequencyAssignment);
    }

    /**
     * @param interference the interference to set
     */
    public void setInterference(double interference) {
        this.interference = interference;
    }

    /**
     * @return the nConflicts
     */
    public int getnConflicts() {
        return nConflicts;
    }

    /**
     * @param nConflicts the nConflicts to set
     */
    public void setnConflicts(int nConflicts) {
        this.nConflicts = nConflicts;
    }

    /**
     *
     * @param dNc
     */
    public void nConfictsDelta(int dNc) {
        this.nConflicts += dNc;
    }

    public double getEvaluation() {
        return this.interference + this.nConflicts * FrequencyAssignmentObjectiveFunction1.K;
    }
}
