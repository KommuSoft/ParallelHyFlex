package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.Operator;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@Operator(token = "#<")
public class SmallerThanConstraint implements IntegerDomainConstraint {

    private static final SmallerThanConstraint instance = new SmallerThanConstraint();

    private SmallerThanConstraint() {
    }

    public static SmallerThanConstraint getInstance() {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        GreaterThanConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        GreaterThanConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        GreaterThanConstraint.getInstance().reduceDomains(i2, i1);
    }
}