package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.utils.Utils;

/**
 * Moves circles in the radius field according to the potential field until no further improvement can be reached
 * @author kommusoft
 */
public class CirclePositioningHeuristicL1 extends LocalSearchHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningHeuristicL1(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        int index = Utils.nextInt(this.getProblem().getNumberOfCircles());
        
    }
}
