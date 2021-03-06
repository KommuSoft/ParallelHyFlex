package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * Modifies the distance of a circle towards the origin
 * @author kommusoft
 */
public class CirclePositioningHeuristicM3 extends MutationHeuristicBase<CirclePositioningSolution,CirclePositioningProblem> {
    
    /**
     *
     * @param problem
     */
    public CirclePositioningHeuristicM3 (CirclePositioningProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int index = Utils.nextInt(this.getProblem().getNumberOfCircles());
        double x = from.getXi(index);
        double y = from.getYi(index);
        double factor = this.getProblem().getLargeCircleRadius()*Utils.nextDouble()/Math.sqrt(x*x+y*y);
        x *= factor;
        y *= factor;
        from.setCircle(this.getProblem(), index, x, y);
    }
    
}