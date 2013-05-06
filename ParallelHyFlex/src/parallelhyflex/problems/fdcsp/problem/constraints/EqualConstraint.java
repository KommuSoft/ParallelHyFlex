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

    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
    
}
