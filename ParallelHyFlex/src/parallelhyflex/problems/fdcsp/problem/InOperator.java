package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;

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
        return new InOperator();
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
        System.out.println("Processed in");
        ((Variable) this.getLeft()).setDomain((FiniteIntegerDomain) this.getRight());
    }
}
