package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic who manipulates the position of a random circle
 * @author kommusoft
 */
public class CirclePositioningHeuristicM1 extends MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningHeuristicM1(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int index = Utils.nextInt(this.getProblem().getNumberOfCircles());
        double sigma = this.getProblem().getLargeCircleRadius(); sigma = 1.0d/(sigma*sigma);
        double dx = sigma*Utils.nextGaussian();
        double dy = sigma*Utils.nextGaussian();
        from.moveCircle(this.getProblem(),index, dx, dy);
    }
}
