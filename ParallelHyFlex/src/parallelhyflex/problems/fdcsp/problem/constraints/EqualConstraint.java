package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.Operator;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@Operator(token="#=")
public class EqualConstraint implements IntegerDomainConstraint {

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        i1.intersectWith(i2);
        i2.intersectWith(i1);
    }
    
}
