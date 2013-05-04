package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.OperatorAnnotation;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@OperatorAnnotation(token = "#\\=")
public class NotEqualConstraint implements IntegerDomainConstraint {

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        if (i1.size() == 1) {
            i2.minusWith(i1.first());
        }
        if (i2.size() == 1) {
            i1.minusWith(i1.first());
            if (i1.size() == 1) {
                i2.minusWith(i1.first());
            }
        }
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        i2.minusWith(i1);
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        i1.minusWith(i2);
    }
}
