package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * A mutation heuristic who swaps two circles
 * @author kommusoft
 */
public class CirclePositioningHeuristicM2 extends MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    /**
     *
     * @param problem
     */
    public CirclePositioningHeuristicM2(CirclePositioningProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int n = this.getProblem().getNumberOfCircles();
        int index0 = Utils.nextInt(n);
        int index1 = Utils.nextInt(n);
        from.swapCircle(this.getProblem(),index0, index1);
    }
    
}
