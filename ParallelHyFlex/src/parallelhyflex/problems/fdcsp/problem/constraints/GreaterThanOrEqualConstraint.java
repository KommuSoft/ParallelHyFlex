package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "#>=")
@OperatorAnnotation()
public class GreaterThanOrEqualConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {

    private static final GreaterThanOrEqualConstraint instance = new GreaterThanOrEqualConstraint();

    public static GreaterThanOrEqualConstraint getInstance() {
        return instance;
    }

    private GreaterThanOrEqualConstraint() {
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        int la = i1.last();
        int fb = i2.first();
        boolean red = i1.intersectWith(fb, la);
        red |= i2.intersectWith(fb, la);
        return red;
    }

    @Override
    public boolean reduceDomains(int i1, FiniteIntegerDomain i2) {
        return i2.intersectWith(i2.first(),i1);
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, int i2) {
        return i1.intersectWith(i2,i1.last());
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
}