package parallelhyflex.problems.threesat.problem;

import parallelhyflex.problemdependent.problem.ObjectiveFunction;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatObjectiveFunction1 implements ObjectiveFunction<ThreeSatSolution> {

    @Override
    public double evaluateSolution(ThreeSatSolution solution) {
        return solution.getConflictingClauses();
    }
    
}
