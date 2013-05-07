package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "#<")
@OperatorAnnotation()
public class SmallerThanConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {

    private static final SmallerThanConstraint instance = new SmallerThanConstraint();

    public static SmallerThanConstraint getInstance() {
        return instance;
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        return GreaterThanConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public boolean reduceDomains(int i1, FiniteIntegerDomain i2) {
        return GreaterThanConstraint.getInstance().reduceDomains(i2, i1);
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, int i2) {
        return GreaterThanConstraint.getInstance().reduceDomains(i2, i1);
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
}