package parallelhyflex.problems.frequencyassignment.problem;

import parallelhyflex.problemdependent.problem.ObjectiveFunctionBase;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentObjectiveFunction1 extends ObjectiveFunctionBase<FrequencyAssignmentSolution> {

    @Override
    public double evaluateSolution(FrequencyAssignmentSolution solution) {
        return solution.getEvaluation();
    }
    
}
