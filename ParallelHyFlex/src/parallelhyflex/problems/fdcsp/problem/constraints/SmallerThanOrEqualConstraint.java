package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.Operator;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@Operator(token = "#<=")
public class SmallerThanOrEqualConstraint implements IntegerDomainConstraint {
    
    private static final SmallerThanOrEqualConstraint instance = new SmallerThanOrEqualConstraint();
    
    private SmallerThanOrEqualConstraint () {}
    
    public static SmallerThanOrEqualConstraint getInstance () {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        GreaterThanOrEqualConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        GreaterThanOrEqualConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        GreaterThanOrEqualConstraint.getInstance().reduceDomains(i2, i1);
    }
}