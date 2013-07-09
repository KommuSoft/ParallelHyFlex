package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * Moves circles in the angle field according to the potential field until no
 * further improvement can be reached
 *
 * @author kommusoft
 */
public class CirclePositioningHeuristicL2 extends LocalSearchHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    /**
     *
     * @param problem
     */
    public CirclePositioningHeuristicL2(CirclePositioningProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from
     */
    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int index = Utils.nextInt(this.getProblem().getNumberOfCircles());
        //TODO

    }
}
