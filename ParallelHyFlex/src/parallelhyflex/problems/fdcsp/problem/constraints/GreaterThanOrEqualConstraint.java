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

    private GreaterThanOrEqualConstraint() {
    }

    public static GreaterThanOrEqualConstraint getInstance() {
        return instance;
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        int la = i1.last();
        int fb = i2.first();
        i1.intersectWith(fb, la);
        i2.intersectWith(fb, la);
    }

    @Override
    public void reduceDomains(int i1, FiniteIntegerDomain i2) {
        i2.minusWith(i1 + 1, i2.last());
    }

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, int i2) {
        i1.minusWith(i1.first(), i2 - 1);
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
}