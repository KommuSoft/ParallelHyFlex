package parallelhyflex.problems.threesat.problem;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.problem.ObjectiveFunctionBase;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatObjectiveFunction1 extends ObjectiveFunctionBase<ThreeSatSolution> {

    /**
     *
     * @param solution
     * @return
     */
    @Override
    public double evaluateSolution(ThreeSatSolution solution) {
        return solution.getConflictingClauses();
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatObjectiveFunction1.class.getName());
}
