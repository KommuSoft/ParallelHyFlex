package parallelhyflex.problems.circlepositioning.problem;

import parallelhyflex.problemdependent.problem.ObjectiveFunctionBase;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 * Calculate the overlapping area together with the outer area
 * @author kommusoft
 */
public class CirclePositioningObjectiveFunction1 extends ObjectiveFunctionBase<CirclePositioningSolution> {

    /**
     *
     * @param solution
     * @return
     */
    @Override
    public double evaluateSolution(CirclePositioningSolution solution) {
        return solution.getOverlapArea()+solution.getOuterArea();
    }
    
}
