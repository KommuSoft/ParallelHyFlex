package parallelhyflex.problems.circlepositioning.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningProblem extends ProblemBase<CirclePositioningSolution,CirclePositioningSolutionGenerator> {
    
    private final double largeCircleRadius;
    private final double[] radia;
    
    public CirclePositioningProblem (double largeCircleRadius, double[] radia) {
        this.largeCircleRadius = largeCircleRadius;
        this.radia = radia;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
