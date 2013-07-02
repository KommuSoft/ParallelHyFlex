package parallelhyflex.problems.circlepositioning.problem;

import parallelhyflex.problemdependent.problem.ObjectiveFunctionBase;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningObjectiveFunction1 extends ObjectiveFunctionBase<CirclePositioningSolution> {

    @Override
    public double evaluateSolution(CirclePositioningSolution solution) {
        return solution.getOverlapArea()+solution.getOuterArea();
    }
    
}
