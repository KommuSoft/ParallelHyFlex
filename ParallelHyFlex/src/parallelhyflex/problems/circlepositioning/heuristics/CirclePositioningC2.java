package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningC2 extends CrossoverHeuristicBase<CirclePositioningSolution,CirclePositioningProblem> {

    public CirclePositioningC2 (CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from1, CirclePositioningSolution from2) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }
    
}
