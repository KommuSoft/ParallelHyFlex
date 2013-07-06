package parallelhyflex.problems.circlepositioning.problem;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningProblemGenerator implements ProblemReader<CirclePositioningSolution,CirclePositioningProblem> {

    public CirclePositioningProblemGenerator () {}
    
    public CirclePositioningProblem generateProblem () {
        double outer = Utils.nextDouble();
        int n = Utils.nextInt(100);
        double maxRadius = Math.sqrt(2.0d*outer/n);
        double[] radia = new double[n];
        for(int i = 0x00; i < n; i++) {
            radia[i] = maxRadius*Utils.nextDouble();
        }
        return new CirclePositioningProblem(outer,radia);
    }
    
    @Override
    public CirclePositioningProblem readAndGenerate(DataInputStream dis) throws IOException {
        double outer = dis.readDouble();
        double[] radia = SerialisationUtils.readDoubleArray(dis);
        return new CirclePositioningProblem(outer,radia);
    }
    
}
