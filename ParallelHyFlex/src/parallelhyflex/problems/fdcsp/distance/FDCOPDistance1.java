package parallelhyflex.problems.fdcsp.distance;

import parallelhyflex.problemdependent.distance.DistanceFunctionBase;
import parallelhyflex.problems.fdcsp.problem.FDCOPProblem;
import parallelhyflex.problems.fdcsp.problem.solution.FDCOPSolution;

/**
 *
 * @author kommusoft
 */
public class FDCOPDistance1 extends DistanceFunctionBase<FDCOPSolution, FDCOPProblem> {

    public FDCOPDistance1(FDCOPProblem problem) {
        super(problem);
    }

    @Override
    public double evaluateDistance(FDCOPSolution solution1, FDCOPSolution solution2) {
        int distance = 0;
        int[] vala = solution1.getVariableValues();
        int[] valb = solution2.getVariableValues();
        for (int i = 0; i < vala.length; i++) {
            if (vala[i] != valb[i]) {
                distance++;
            }
        }
        return distance;
    }

    @Override
    public boolean evaluateDistanceSmallerThanOrEqual(FDCOPSolution solution1, FDCOPSolution solution2, double maxDistance) {
        int distance = 0;
        int[] vala = solution1.getVariableValues();
        int[] valb = solution2.getVariableValues();
        for (int i = 0; i < vala.length; i++) {
            if (vala[i] != valb[i]) {
                distance++;
                if (distance > maxDistance) {
                    return false;
                }
            }
        }
        return true;
    }
}
