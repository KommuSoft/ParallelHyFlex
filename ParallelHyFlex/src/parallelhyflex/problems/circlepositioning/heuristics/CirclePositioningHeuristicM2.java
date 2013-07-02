package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic who swaps two circles
 * @author kommusoft
 */
public class CirclePositioningHeuristicM2 extends MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningHeuristicM2(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int n = this.getProblem().getNumberOfCircles();
        int index0 = Utils.StaticRandom.nextInt(n);
        int index1 = Utils.StaticRandom.nextInt(n);
        from.swapCircle(this.getProblem(),index0, index1);
    }
    
}
