package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentProblemGenerator implements ProblemReader<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    @Override
    public FrequencyAssignmentProblem readAndGenerate(DataInputStream dis) throws IOException {
        int nTransceivers = dis.readInt();
        int nSectors = dis.readInt();
        int[][] frequencies = SerialisationUtils.readIntArray2d(dis);
        double[][] means = SerialisationUtils.readDoubleArray2d(dis);
        double[][] stdevs = SerialisationUtils.readDoubleArray2d(dis);
        int[] placement = SerialisationUtils.readIntArray(dis);
        return new FrequencyAssignmentProblem(nTransceivers, nSectors, frequencies, means, stdevs, placement);
    }
}
