package parallelhyflex.problems.fdcsp.problem.constraints;

import java.util.regex.Pattern;
import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.grammar.OperatorType;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGenerator;
import parallelhyflex.parsing.tokenizing.TokenGeneratorImplementation;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token="minimizing")
@OperatorAnnotation(operatorType=OperatorType.BindRight)
public class MinimizingOperator extends OperatorBase<Token,Token> implements TokenGenerator<MinimizingOperator> {

    private static final MinimizingOperator instance = new MinimizingOperator();
    
    public static MinimizingOperator getInstance() {
        return instance;
    }

    @Override
    public boolean canSetLeft(Token token) {
        return false;
    }

    @Override
    public boolean canSetRight(Token token) {
        return (token instanceof Expression);
    }

    @Override
    public void process() {
        System.out.println(String.format("I will minimize %s",this.getRight()));
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

    @Override
    public MinimizingOperator generate(String variable) {
        return new MinimizingOperator();
    }
}
