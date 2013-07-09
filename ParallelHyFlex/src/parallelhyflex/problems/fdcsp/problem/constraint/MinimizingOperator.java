package parallelhyflex.problems.fdcsp.problem.constraint;

import java.util.regex.Pattern;
import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.grammar.OperatorType;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGenerator;
import parallelhyflex.parsing.tokenizing.TokenGeneratorImplementation;
import parallelhyflex.problems.fdcsp.problem.expression.Expression;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "minimizing")
@OperatorAnnotation(operatorType = OperatorType.BindRight)
public class MinimizingOperator extends OperatorBase<Token, Token> implements TokenGenerator<MinimizingOperator> {

    private static final MinimizingOperator instance = new MinimizingOperator();

    /**
     *
     * @return
     */
    public static MinimizingOperator getInstance() {
        return instance;
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean canSetLeft(Token token) {
        return false;
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean canSetRight(Token token) {
        return (token instanceof Expression);
    }

    /**
     *
     * @return
     */
    public Expression getExpression() {
        return (Expression) this.getRight();
    }

    /**
     *
     * @param text
     * @return
     */
    @Override
    public boolean validate(String text) {
        return TokenGeneratorImplementation.validate(this, text);
    }

    /**
     *
     * @return
     */
    @Override
    public double getPriority() {
        return TokenGeneratorImplementation.getPriority(this);
    }

    /**
     *
     * @return
     */
    @Override
    public Pattern getPattern() {
        return TokenGeneratorImplementation.getPattern(this);
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public MinimizingOperator generate(String variable) {
        return new MinimizingOperator();
    }
}
