package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "#\\=")
@OperatorAnnotation()
public class NotEqualConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {
    
    private static final NotEqualConstraint instance = new NotEqualConstraint();
    
    public static NotEqualConstraint getInstance () {
        return instance;
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        boolean red = false;
        if (i1.size() == 1) {
            red |= i2.minusWith(i1.low());
        }
        if (i2.size() == 1) {
            red |= i1.minusWith(i1.low());
            if (i1.size() == 1) {
                red |= i2.minusWith(i1.low());
            }
        }
        return red;
    }

    @Override
    public boolean reduceDomains(int i1, FiniteIntegerDomain i2) {
        return i2.minusWith(i1);
    }

    @Override
    public boolean reduceDomains(FiniteIntegerDomain i1, int i2) {
        return i1.minusWith(i2);
    }
    
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
}
