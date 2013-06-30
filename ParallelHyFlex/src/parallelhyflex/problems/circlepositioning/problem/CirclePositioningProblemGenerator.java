package parallelhyflex.problems.circlepositioning.problem;

import java.io.DataInputStream;
import java.io.IOException;
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
        double outer = Utils.StaticRandom.nextDouble();
        int n = Utils.StaticRandom.nextInt(100);
        double maxRadius = Math.sqrt(2.0d*outer/n);
        double[] radia = new double[n];
        for(int i = 0x00; i < n; i++) {
            radia[i] = maxRadius*Utils.StaticRandom.nextDouble();
        }
        return new CirclePositioningProblem(outer,radia);
    }
    
    @Override
    public CirclePositioningProblem readAndGenerate(DataInputStream dis) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
