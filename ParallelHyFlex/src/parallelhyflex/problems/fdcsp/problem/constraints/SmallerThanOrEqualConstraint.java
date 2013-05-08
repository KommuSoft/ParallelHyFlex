package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "#<=")
@OperatorAnnotation()
public class SmallerThanOrEqualConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {

    private static final SmallerThanOrEqualConstraint instance = new SmallerThanOrEqualConstraint();

    public static SmallerThanOrEqualConstraint getInstance() {
        return instance;
    }

    private SmallerThanOrEqualConstraint() {
    }

    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, MutableFiniteIntegerDomain i2) {
        return GreaterThanOrEqualConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public boolean reduceDomains(int i1, MutableFiniteIntegerDomain i2) {
        return GreaterThanOrEqualConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, int i2) {
        return GreaterThanOrEqualConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
}