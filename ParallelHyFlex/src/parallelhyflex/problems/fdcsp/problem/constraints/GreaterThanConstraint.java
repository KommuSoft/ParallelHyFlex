package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="#>")
@OperatorAnnotation()
public class GreaterThanConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {
    
    private static final GreaterThanConstraint instance = new GreaterThanConstraint();
    
    public static GreaterThanConstraint getInstance () {
        return instance;
    }
    
    private GreaterThanConstraint () {}

    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, MutableFiniteIntegerDomain i2) {
        int la = i1.high();
        int fb = i2.low();
        //TODO: carefull with integerbounds
        boolean red = i1.intersectWith(fb+1, la);
        red |= i2.intersectWith(fb, la-1);
        return red;
    }

    @Override
    public boolean reduceDomains(int i1, MutableFiniteIntegerDomain i2) {
        return i2.intersectWith(i2.low(), i1-1);
    }

    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, int i2) {
        return i1.minusWith(i2+1, i1.high());
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
    
}