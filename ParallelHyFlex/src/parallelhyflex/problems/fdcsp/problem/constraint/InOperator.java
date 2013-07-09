package parallelhyflex.problems.fdcsp.problem.constraint;

import java.util.regex.Pattern;
import parallelhyflex.parsing.grammar.OperatorAnnotation;
import parallelhyflex.parsing.grammar.OperatorBase;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGenerator;
import parallelhyflex.parsing.tokenizing.TokenGeneratorImplementation;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;
import parallelhyflex.problems.fdcsp.problem.Variable;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "in")
@OperatorAnnotation()
public class InOperator extends OperatorBase<Token,Token> implements TokenGenerator<InOperator> {
    
    /**
     *
     * @param variable
     * @return
     */
    @Override
    public InOperator generate(String variable) {
        return new InOperator();
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean canSetLeft(Token token) {
        return(token instanceof Variable);
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public boolean canSetRight(Token token) {
        return(token instanceof MutableFiniteIntegerDomain);
    }

    /**
     *
     */
    @Override
    public void process() {
        ((Variable) this.getLeft()).setDomain((MutableFiniteIntegerDomain) this.getRight());//TODO: intersect domain? (multiple ins for the same variable?)
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
}
