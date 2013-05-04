package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.OperatorAnnotation;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@OperatorAnnotation(token = "#>=")
public class GreaterThanOrEqualConstraint implements IntegerDomainConstraint {

    private static final GreaterThanOrEqualConstraint instance = new GreaterThanOrEqualConstraint();

    private GreaterThanOrEqualConstraint() {
    }

    public static GreaterThanOrEqualConstraint getInstance() {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        int fa = i1.first();
        int la = i1.last();
        int fb = i2.first();
        int lb = i2.last();
        i1.minusWith(fa, fb - 1);
        i2.minusWith(la + 1, lb);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        i2.minusWith(i1+1, i2.last());
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        i1.minusWith(i1.first(), i2-1);
    }
}