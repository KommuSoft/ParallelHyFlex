package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 * A crossover heuristic
 * @author kommusoft
 */
public class CirclePositioningHeuristicC3 extends CrossoverHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningHeuristicC3(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from1, CirclePositioningSolution from2) {
        //TODO
    }
}
