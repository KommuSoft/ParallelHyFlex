package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.TokenAnnotation;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="#=")
public class EqualConstraint implements IntegerDomainConstraint {
    
    private static final EqualConstraint instance = new EqualConstraint();
    
    private EqualConstraint () {}
    
    public static EqualConstraint getInstance () {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        i1.intersectWith(i2);
        i2.intersectWith(i1);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        i2.intersectWith(i1);
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        i1.intersectWith(i2);
    }
    
}
