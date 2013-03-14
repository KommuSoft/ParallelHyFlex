package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.ObjectiveFunction;

/**
 *
 * @author kommusoft
 */
public class ThreeSatObjectiveFunction implements ObjectiveFunction<ThreeSatSolution> {

    @Override
    public double evaluateSolution(ThreeSatSolution solution) {
        return solution.getConflictingClauses();
    }
    
}
