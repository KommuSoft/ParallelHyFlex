package parallelhyflex.problems.frequencyassignment.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentSolution implements Solution<FrequencyAssignmentSolution> {

    private final int[] frequencyAssignment;
    private double evaluation;

    public FrequencyAssignmentSolution(double evaluation, int[] frequencyAssignment) {
        this.frequencyAssignment = frequencyAssignment;
        this.evaluation = evaluation;
    }

    public FrequencyAssignmentSolution(int[] frequencyAssignment, FrequencyAssignmentProblem problem) {
        this.frequencyAssignment = frequencyAssignment;
        this.evaluation = FrequencyAssignmentUtils.evaluate(problem, frequencyAssignment);
    }

    public FrequencyAssignmentSolution(int[] frequencyAssignment, double evaluation) {
        this(evaluation, frequencyAssignment);
    }

    @Override
    public FrequencyAssignmentSolution clone() {
        int[] data = new int[this.getFrequencyAssignment().length];
        System.arraycopy(this.getFrequencyAssignment(), 0, data, 0, data.length);
        return new FrequencyAssignmentSolution(data, this.getEvaluation());
    }

    @Override
    public boolean equalSolution(FrequencyAssignmentSolution other) {
        return (Utils.isEqualTolerance(this.getEvaluation(), other.getEvaluation()) && Utils.arrayEquality(getFrequencyAssignment(), other.getFrequencyAssignment()));
    }

    @Override
    public boolean hasFastDifferenceWith(FrequencyAssignmentSolution other) {
        return false;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.evaluation = dis.readDouble();
        SerialisationUtils.readIntArray(dis, getFrequencyAssignment());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeDouble(this.getEvaluation());
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
    public double getEvaluation() {
        return evaluation;
    }

    public void evaluationDelta(double dEvaluation) {
        this.evaluation += dEvaluation;
    }

    public double calculateEvaluation(FrequencyAssignmentProblem tsp) {
        return FrequencyAssignmentUtils.evaluate(tsp, this.frequencyAssignment);
    }
}
