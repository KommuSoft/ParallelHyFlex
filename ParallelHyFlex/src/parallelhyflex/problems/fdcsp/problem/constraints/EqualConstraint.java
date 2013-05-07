package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="#=")
@OperatorAnnotation()
public class EqualConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {
    
    private static final EqualConstraint instance = new EqualConstraint();
    
    public static EqualConstraint getInstance () {
        return instance;
    }
    
    private EqualConstraint () {}

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        boolean red = i1.intersectWith(i2);
        red |= i2.intersectWith(i1);
        return red;
    }

    @Override
    public boolean reduceDomains(int i1, FiniteIntegerDomain i2) {
        return i2.intersectWith(i1);
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, int i2) {
        return i1.intersectWith(i2);
    }

    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
    
}
