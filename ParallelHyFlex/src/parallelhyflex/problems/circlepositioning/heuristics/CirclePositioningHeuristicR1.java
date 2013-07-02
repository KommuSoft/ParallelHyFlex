package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.RuinRecreateHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningHeuristicR1 extends RuinRecreateHeuristicBase<CirclePositioningSolution,CirclePositioningProblem> {

    public CirclePositioningHeuristicR1 (CirclePositioningProblem problem) {
        super(problem);
    }
    
    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from) {
        //TODO
    }
    
}
