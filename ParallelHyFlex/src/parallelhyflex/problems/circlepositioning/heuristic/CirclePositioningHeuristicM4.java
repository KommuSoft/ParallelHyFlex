package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningHeuristicM4 extends MutationHeuristicBase<CirclePositioningSolution,CirclePositioningProblem> {
    
    public CirclePositioningHeuristicM4 (CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        double R = this.getProblem().getLargeCircleRadius();
        double r = Utils.nextDouble()*R;
        double theta = 2.0d*Math.PI*Utils.nextDouble();
        double x = r*Math.cos(theta);
        double y = r*Math.sin(theta);
        int index = Utils.nextInt(this.getProblem().getNumberOfCircles());
        from.setCircle(this.getProblem(), index, x, y);
    }
    
}