package parallelhyflex.problems.circlepositioning.solution;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.problemdependent.solution.SolutionGeneratorBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CirclePositioningSolution readAndGenerate(DataInputStream dis) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
