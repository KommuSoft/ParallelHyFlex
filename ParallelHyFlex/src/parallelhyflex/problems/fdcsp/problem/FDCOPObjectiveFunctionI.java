package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.problemdependent.problem.ObjectiveFunctionBase;
import parallelhyflex.problems.fdcsp.problem.solution.FDCOPSolution;

/**
 *
 * @author kommusoft
 */
public class FDCOPObjectiveFunctionI extends ObjectiveFunctionBase<FDCOPSolution> {
    
    private final int index;
    
    /**
     *
     * @param index
     */
    public FDCOPObjectiveFunctionI (int index) {
        this.index = index;
    }

    /**
     *
     * @param solution
     * @return
     */
    @Override
    public double evaluateSolution(FDCOPSolution solution) {
        return solution.getEvaluationValue(this.index);
    }
    
}
