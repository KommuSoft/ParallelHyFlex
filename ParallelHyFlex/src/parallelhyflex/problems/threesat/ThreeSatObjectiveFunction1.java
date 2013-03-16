package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.ObjectiveFunction;

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
