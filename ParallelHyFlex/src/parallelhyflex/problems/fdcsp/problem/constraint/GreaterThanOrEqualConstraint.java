package parallelhyflex.problems.fdcsp.problem.constraint;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;

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
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, MutableFiniteIntegerDomain i2) {
        int la = i1.high();
        int fb = i2.low();
        boolean red = i1.intersectWith(fb, la);
        red |= i2.intersectWith(fb, la);
        return red;
    }

    @Override
    public boolean reduceDomains(int i1, MutableFiniteIntegerDomain i2) {
        return i2.intersectWith(i2.low(),i1);
    }

    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, int i2) {
        return i1.intersectWith(i2,i1.high());
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
}