package parallelhyflex.problems.fdcsp.problem.constraint;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "#!=")
@OperatorAnnotation()
public class NotEqualConstraint extends TokenGeneratorBase<IntegerDomainConstraintOperator> implements IntegerDomainConstraint {
    
    private static final NotEqualConstraint instance = new NotEqualConstraint();
    
    /**
     *
     * @return
     */
    public static NotEqualConstraint getInstance () {
        return instance;
    }

    /**
     *
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, MutableFiniteIntegerDomain i2) {
        boolean red = false;
        System.out.println(String.format("Reducing %s #!= %s",i1,i2));
        if (i1.size() == 1) {
            red |= i2.minusWith(i1.low());
            System.out.println(String.format("Step1 %s #!= %s",i1,i2));
        }
        if (i2.size() == 1) {
            red |= i1.minusWith(i1.low());
            System.out.println(String.format("Step2 %s #!= %s",i1,i2));
            if (i1.size() == 1) {
                red |= i2.minusWith(i1.low());
                System.out.println(String.format("Step3 %s #!= %s",i1,i2));
            }
        }
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
        return i2.minusWith(i1);
    }

    /**
     *
     * @param i1
     * @param i2
     * @return
     */
    @Override
    public boolean reduceDomains(MutableFiniteIntegerDomain i1, int i2) {
        return i1.minusWith(i2);
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
