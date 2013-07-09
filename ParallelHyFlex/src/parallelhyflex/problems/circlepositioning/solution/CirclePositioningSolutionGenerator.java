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
public class CirclePositioningSolutionGenerator extends SolutionGeneratorBase<CirclePositioningSolution, CirclePositioningProblem> {

    /**
     *
     * @param problem
     */
    public CirclePositioningSolutionGenerator(CirclePositioningProblem problem) {
        super(problem);
    }

    /**
     *
     * @return
     */
    @Override
    public CirclePositioningSolution generateSolution() {
        int n = this.getProblem().getNumberOfCircles() << 0x01;
        double or = this.getProblem().getLargeCircleRadius();
        double[] positions = new double[n];
        for (int i = 0; i < n;) {
            double r = or * Utils.nextDouble();
            double alpha = 2.0d * Math.PI * Utils.nextDouble();
            double x = r * Math.cos(alpha);
            double y = r * Math.sin(alpha);
            positions[i++] = x;
            positions[i++] = y;
        }
        return new CirclePositioningSolution(positions, this.getProblem());
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public CirclePositioningSolution readAndGenerate(DataInputStream dis) throws IOException {
        double[] positions = SerialisationUtils.readDoubleArray(dis);
        double overlapArea = dis.readDouble();
        double outerArea = dis.readDouble();
        return new CirclePositioningSolution(positions, overlapArea, outerArea);
    }
}