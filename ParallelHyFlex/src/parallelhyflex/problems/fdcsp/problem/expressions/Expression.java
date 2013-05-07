package parallelhyflex.problems.fdcsp.problem.expressions;

import parallelhyflex.problems.fdcsp.problem.solution.FDCOPSolution;

/**
 *
 * @author kommusoft
 */
public interface Expression {
    
    double getExpressionValue (FDCOPSolution solution);
    
}
