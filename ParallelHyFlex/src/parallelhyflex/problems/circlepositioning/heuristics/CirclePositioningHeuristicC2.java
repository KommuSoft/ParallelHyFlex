package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 * A crossover heuristic where the new positions of the circles is the average of the positions of the old circles.
 * @author kommusoft
 */
public class CirclePositioningHeuristicC2 extends CrossoverHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> {

    public CirclePositioningHeuristicC2(CirclePositioningProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(CirclePositioningSolution from1, CirclePositioningSolution from2) {
        int n = this.getProblem().getNumberOfCircles();
        double[] pa = from1.getPositions();
        double[] pb = from2.getPositions();
        double x, y;
        for (int i = 0x00, i2 = 0x00; i < n; i++) {
            x = 0.5d * (pa[i2] + pb[i2++]);
            y = 0.5d * (pa[i2] + pb[i2++]);
            from1.setCircle(this.getProblem(), i, x, y);
        }
    }
}
