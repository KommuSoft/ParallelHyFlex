package parallelhyflex.problems.frequencyassignment.solution;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.solution.SolutionGeneratorBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentSolutionGenerator extends SolutionGeneratorBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentSolutionGenerator(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    /**
     *
     * @return
     */
    @Override
    public FrequencyAssignmentSolution generateSolution() {
        int[][] frequencies = this.getProblem().getFrequencies();
        int n = frequencies.length;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = ProbabilityUtils.randomElement(frequencies[i]);
        }
        return new FrequencyAssignmentSolution(values, this.getProblem());
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public FrequencyAssignmentSolution readAndGenerate(DataInputStream dis) throws IOException {
        double inf = dis.readDouble();
        int nco = dis.readInt();
        int[] freq = SerialisationUtils.readIntArray(dis);
        return new FrequencyAssignmentSolution(freq, inf, nco);
    }
}
