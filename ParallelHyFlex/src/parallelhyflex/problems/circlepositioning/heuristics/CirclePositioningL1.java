package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 * Moves circles in the X/Y field according to the potential field until no further improvement can be reached
 * @author kommusoft
 */
public class CirclePositioningL1 extends LocalSearchHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningL1(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        
    }
}
