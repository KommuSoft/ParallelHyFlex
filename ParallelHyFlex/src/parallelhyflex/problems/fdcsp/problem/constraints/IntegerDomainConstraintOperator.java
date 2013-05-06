package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.problems.fdcsp.problem.Variable;

/**
 *
 * @author kommusoft
 */
@OperatorAnnotation()
public class IntegerDomainConstraintOperator extends OperatorBase {

    private final IntegerDomainConstraint constraint;

    public IntegerDomainConstraintOperator(IntegerDomainConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean canSetLeft(Token token) {
        return (token instanceof Variable);
    }

    @Override
    public boolean canSetRight(Token token) {
        return (token instanceof Variable);
    }

    @Override
    public void process() {
        Variable vl = (Variable) this.getLeft();
        Variable vr = (Variable) this.getRight();
        System.out.println(String.format("reducing domains of %s=%s and %s=%s", vl, vl.getDomain(), vr, vr.getDomain()));
        this.constraint.reduceDomains(vl.getDomain(), vr.getDomain());
        System.out.println(String.format("now they are: %s=%s and %s=%s", vl, vl.getDomain(), vr, vr.getDomain()));
    }
}
