package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="#>")
public class GreaterThanConstraint implements IntegerDomainConstraint {
    
    private static final GreaterThanConstraint instance = new GreaterThanConstraint();
    
    private GreaterThanConstraint () {}
    
    public static GreaterThanConstraint getInstance () {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        int fa = i1.first();
        int la = i1.last();
        int fb = i2.first();
        int lb = i2.last();
        i1.minusWith(fa, fb);
        i2.minusWith(la, lb);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        i2.minusWith(i1, i2.last());
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        i1.minusWith(i1.first(), i2);
    }
    
}