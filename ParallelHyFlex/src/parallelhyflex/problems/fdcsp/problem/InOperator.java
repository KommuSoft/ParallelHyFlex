package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.OperatorAnnotation;
import parallelhyflex.parsing.OperatorBase;
import parallelhyflex.parsing.Token;
import parallelhyflex.parsing.TokenAnnotation;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "in")
@OperatorAnnotation()
public class InOperator extends OperatorBase<InOperator,Token,Token> {

    private static final InOperator instance = new InOperator();

    public static InOperator getInstance() {
        return instance;
    }

    @Override
    public InOperator generate(String variable) {
        return this;
    }

    @Override
    public boolean canSetLeft(Token token) {
        return(token instanceof Variable);
    }

    @Override
    public boolean canSetRight(Token token) {
        return(token instanceof FiniteIntegerDomain);
    }

    @Override
    public void process() {
        ((Variable) this.getLeft()).setDomain((FiniteIntegerDomain) this.getRight());
    }
}
