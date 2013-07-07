package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.algebra.DoubleUpperMatrix;
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
        DoubleUpperMatrix means = new DoubleUpperMatrix(0x00);
        means.read(dis);
        DoubleUpperMatrix stdevs = new DoubleUpperMatrix(0x00);
        stdevs.read(dis);
        int[] placement = SerialisationUtils.readIntArray(dis);
        return new FrequencyAssignmentProblem(nTransceivers, nSectors, frequencies, means, stdevs, placement);
    }
    
    public FrequencyAssignmentProblem generateProblem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
