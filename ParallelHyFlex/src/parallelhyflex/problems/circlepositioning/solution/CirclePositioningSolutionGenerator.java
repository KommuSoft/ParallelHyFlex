package parallelhyflex.problems.circlepositioning.solution;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.solution.SolutionGeneratorBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningSolutionGenerator extends SolutionGeneratorBase<CirclePositioningSolution,CirclePositioningProblem> {
    
    public CirclePositioningSolutionGenerator (CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public CirclePositioningSolution generateSolution() {
        int n = this.getProblem().getNumberOfCircles();
        double or = this.getProblem().getLargeCircleRadius();
        double[] positions = new double[n<<0x01];
        for(int i = 0; i < n;) {
            double r = or*Utils.StaticRandom.nextDouble();
            double alpha = 2.0d*Math.PI*Utils.StaticRandom.nextDouble();
            double x = r*Math.cos(alpha);
            double y = r*Math.sin(alpha);
            positions[i++] = x;
            positions[i++] = y;
        }
        return new CirclePositioningSolution(positions,0.0d);
    }

    @Override
    public CirclePositioningSolution readAndGenerate(DataInputStream dis) throws IOException {
        double[] positions = SerialisationUtils.readDoubleArray(dis);
        double overlapArea = dis.readDouble();
        return new CirclePositioningSolution(positions,overlapArea);
    }
    
}
