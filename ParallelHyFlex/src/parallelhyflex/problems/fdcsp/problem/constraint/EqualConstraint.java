package parallelhyflex.problems.fdcsp.problem.constraint;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="#=")
@OperatorAnnotation()
public class EqualConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {
    
    private static final EqualConstraint instance = new EqualConstraint();
    
    /**
     *
     * @return
     */
    public static EqualConstraint getInstance () {
        return instance;
    }
    
    private EqualConstraint () {}

    /**
     *
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, MutableFiniteIntegerDomain i2) {
        boolean red = i1.intersectWith(i2);
        red |= i2.intersectWith(i1);
        return red;
    }

    /**
     *
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean reduceDomains(int i1, MutableFiniteIntegerDomain i2) {
        return i2.intersectWith(i1);
    }

    /**
     *
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, int i2) {
        return i1.intersectWith(i2);
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public IntegerDomainConstraintOperator generate(String variable) {
        return new IntegerDomainConstraintOperator(getInstance());
    }
    
}
