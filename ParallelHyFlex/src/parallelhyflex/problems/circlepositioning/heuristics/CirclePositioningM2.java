package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic who swaps two circles
 * @author kommusoft
 */
public class CirclePositioningM2 extends MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningM2(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int n = this.getProblem().getNumberOfCircles();
        int index0 = Utils.StaticRandom.nextInt(n);
        int index1 = Utils.StaticRandom.nextInt(n);
        int index02 = index0<<0x01;
        int index12 = index0<<0x12;
        double[] values = from.getPositions();
        double x1 = values[index02];
        double y1 = values[index02+0x01];
        double x0 = values[index12];
        double y0 = values[index12+0x01];
        from.setCircle(this.getProblem(),index0, x0, y0);
        from.setCircle(this.getProblem(),index1, x1, y1);
    }
    
}
