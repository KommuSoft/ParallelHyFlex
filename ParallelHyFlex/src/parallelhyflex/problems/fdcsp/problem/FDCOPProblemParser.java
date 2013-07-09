package parallelhyflex.problems.fdcsp.problem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import parallelhyflex.parsing.ParsingException;
import parallelhyflex.parsing.grammar.OperatorBinder;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenStreamParser;
import parallelhyflex.problems.fdcsp.problem.constraint.EqualConstraint;
import parallelhyflex.problems.fdcsp.problem.constraint.GreaterThanConstraint;
import parallelhyflex.problems.fdcsp.problem.constraint.GreaterThanOrEqualConstraint;
import parallelhyflex.problems.fdcsp.problem.constraint.InOperator;
import parallelhyflex.problems.fdcsp.problem.constraint.MinimizingOperator;
import parallelhyflex.problems.fdcsp.problem.constraint.NotEqualConstraint;
import parallelhyflex.problems.fdcsp.problem.constraint.SmallerThanConstraint;
import parallelhyflex.problems.fdcsp.problem.constraint.SmallerThanOrEqualConstraint;
import parallelhyflex.problems.fdcsp.problem.expression.Expression;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblemParser {

    private static TokenStreamParser<Token> tokenParser;
    private static VariableTokenGenerator variableToken = new VariableTokenGenerator();

    /**
     *
     * @return
     */
    public static TokenStreamParser getTokenParser() {
        if (tokenParser == null) {
            tokenParser = new TokenStreamParser();
            tokenParser.addToken(new MutableFiniteIntegerDomain());
            tokenParser.addToken(new InOperator());
            tokenParser.addToken(variableToken);
            tokenParser.addToken(EqualConstraint.getInstance());
            tokenParser.addToken(NotEqualConstraint.getInstance());
            tokenParser.addToken(GreaterThanOrEqualConstraint.getInstance());
            tokenParser.addToken(GreaterThanConstraint.getInstance());
            tokenParser.addToken(SmallerThanOrEqualConstraint.getInstance());
            tokenParser.addToken(SmallerThanConstraint.getInstance());
            tokenParser.addToken(MinimizingOperator.getInstance());
        }
        return tokenParser;
    }

    /**
     *
     * @param stream
     * @return
     * @throws ParsingException
     */
    public FDCOPProblem parse(InputStream stream) throws ParsingException {
        OperatorBinder ob = new OperatorBinder();
        Iterable<Token> tokens = ob.bind(getTokenParser().getIterable(stream));
        ArrayList<Expression> mini = new ArrayList<>();
        for (Token t : tokens) {
            if (t instanceof MinimizingOperator) {
                mini.add(((MinimizingOperator) t).getExpression());
            }
        }
        VariableStore vs = variableToken.getVariableStore();
        Variable[] vars = new Variable[vs.size()];
        int i = 0;
        for (Variable v : vs) {
            vars[i++] = v;
        }
        return new FDCOPProblem(vars, mini.toArray(new Expression[mini.size()]));
    }

    /**
     *
     * @param text
     * @return
     * @throws IOException
     * @throws ParsingException
     */
    public FDCOPProblem parse(String text) throws IOException, ParsingException {
        FDCOPProblem result;
        try (InputStream is = new ByteArrayInputStream(text.getBytes())) {
            result = parse(is);
        }
        return result;
    }
}
