package parallelhyflex.problems.circlepositioning.heuristic;

import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 * A crossover heuristic
 * @author kommusoft
 */
public class CirclePositioningHeuristicC3 extends CrossoverHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    /**
     *
     * @param problem
     */
    public CirclePositioningHeuristicC3(CirclePositioningProblem problem) {
        super(problem);
    }

    /**
     *
     * @param from1
     * @param from2
     */
    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from1, CirclePositioningSolution from2) {
        //TODO
    }
}
