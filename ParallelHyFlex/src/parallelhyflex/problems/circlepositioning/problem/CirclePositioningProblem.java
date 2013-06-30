package parallelhyflex.problems.circlepositioning.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningProblem extends ProblemBase<CirclePositioningSolution, CirclePositioningSolutionGenerator> {
    
    private final double largeCircleRadius;
    private final double[] radia;
    
    public CirclePositioningProblem(double largeCircleRadius, double[] radia) {
        this.largeCircleRadius = largeCircleRadius;
        this.radia = radia;
    }
    
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeDouble(getLargeCircleRadius());
        SerialisationUtils.writeDoubleArray(dos, getRadia());
    }
    
    public int getNumberOfCircles () {
        return this.radia.length;
    }

    /**
     * @return the largeCircleRadius
     */
    public double getLargeCircleRadius() {
        return largeCircleRadius;
    }

    /**
     * @return the radia
     */
    public double[] getRadia() {
        return radia;
    }
}
