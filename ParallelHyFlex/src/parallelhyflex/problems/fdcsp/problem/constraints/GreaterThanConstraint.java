package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="#>")
@OperatorAnnotation()
public class GreaterThanConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {
    
    private static final GreaterThanConstraint instance = new GreaterThanConstraint();
    
    private GreaterThanConstraint () {}
    
    public static GreaterThanConstraint getInstance () {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        int la = i1.last();
        int fb = i2.first();
        i1.intersectWith(fb+1, la);
        i2.intersectWith(fb, la-1);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        i2.minusWith(i1, i2.last());
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        i1.minusWith(i1.first(), i2);
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
    
}