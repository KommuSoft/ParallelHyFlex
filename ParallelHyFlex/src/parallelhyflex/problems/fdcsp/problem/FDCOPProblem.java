package parallelhyflex.problems.fdcsp.problem;

import java.util.Arrays;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblem {

    private Variable[] variables;
    private FDCOPConstraint[] constraints;
    private Expression[] minimalisation;

    public FDCOPProblem(Variable[] variables, FDCOPConstraint[] constraints, Expression[] minimalisations) {
        System.out.println(String.format("FDCOPP %s %s %s", Arrays.toString(variables), Arrays.toString(constraints), Arrays.toString(minimalisations)));
    }
}