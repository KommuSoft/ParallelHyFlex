package parallelhyflex.problems.fdcsp.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblem {

    private Variable[] variables;
    private FDCOPConstraint[] constraints;
    private HashMap<Variable,ArrayList<FDCOPConstraint>> constraintMapping = new HashMap<>();
    private Expression[] minimalisation;

    public FDCOPProblem(Variable[] variables, FDCOPConstraint[] constraints, Expression[] minimalisations) {
        System.out.println(String.format("FDCOPP %s %s %s", Arrays.toString(variables), Arrays.toString(constraints), Arrays.toString(minimalisations)));
        //TODO: reduce variable domains further
    }
}