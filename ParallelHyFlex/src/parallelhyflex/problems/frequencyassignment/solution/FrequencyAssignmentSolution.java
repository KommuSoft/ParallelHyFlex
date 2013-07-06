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
        int[] data = new int[this.frequencyAssignment.length];
        System.arraycopy(this.frequencyAssignment, 0, data, 0, data.length);
        return new FrequencyAssignmentSolution(data, this.evaluation);
    }

    @Override
    public boolean equalSolution(FrequencyAssignmentSolution other) {
        return (Utils.isEqualTolerance(this.evaluation, other.evaluation) && Utils.arrayEquality(frequencyAssignment, other.frequencyAssignment));
    }

    @Override
    public boolean hasFastDifferenceWith(FrequencyAssignmentSolution other) {
        return false;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        this.evaluation = dis.readDouble();
        SerialisationUtils.readIntArray(dis, frequencyAssignment);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeDouble(this.evaluation);
        SerialisationUtils.writeIntArray(dos, frequencyAssignment);
    }
}
