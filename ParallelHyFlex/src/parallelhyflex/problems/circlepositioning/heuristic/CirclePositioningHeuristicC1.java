package parallelhyflex.problems.circlepositioning.heuristic;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * Sets the locations of the circles pointwise the probability is based on the evaluation function of both solutions.
 * @author kommusoft
 */
public class CirclePositioningHeuristicC1 extends CrossoverHeuristicBase<CirclePositioningSolution,CirclePositioningProblem> {
    private static final Logger LOG = Logger.getLogger(CirclePositioningHeuristicC1.class.getName());
    
    /**
     *
     * @param problem
     */
    public CirclePositioningHeuristicC1 (CirclePositioningProblem problem) {
        super(problem);
    }
    
    /**
     *
     * @param from1
     * @param from2
     */
    @Override
    public void applyHeuristicLocally (CirclePositioningSolution from1, CirclePositioningSolution from2) {
        double x, y;
        double[] posb = from2.getPositions();
        double pbt = from1.getDefaultEvaluation();
        double pb = pbt/(pbt+from2.getDefaultEvaluation());
        int n = this.getProblem().getNumberOfCircles();
        for(int i = 0, i2 = 0; i < n; i++) {
            if(Utils.nextDouble() < pb) {
                x = posb[i2++];
                y = posb[i2++];
                from1.setCircle(this.getProblem(), i, x, y);
            }
            else {
                i2 += 0x02;
            }
        }
    }
    
}
