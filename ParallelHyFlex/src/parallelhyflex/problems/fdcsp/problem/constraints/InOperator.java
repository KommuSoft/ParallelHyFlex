package parallelhyflex.problems.fdcsp.problem.constraints;

import java.util.regex.Pattern;
import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGenerator;
import parallelhyflex.parsing.tokenizing.TokenGeneratorImplementation;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;
import parallelhyflex.problems.fdcsp.problem.Variable;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "in")
@OperatorAnnotation()
public class InOperator extends OperatorBase<Token,Token> implements TokenGenerator<InOperator> {
    
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

    @Override
    public boolean validate(String text) {
        return TokenGeneratorImplementation.validate(this, text);
    }

    @Override
    public double getPriority() {
        return TokenGeneratorImplementation.getPriority(this);
    }

    @Override
    public Pattern getPattern() {
        return TokenGeneratorImplementation.getPattern(this);
    }
}
