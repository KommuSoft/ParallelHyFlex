package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.problems.fdcsp.problem.constraints.InOperator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import parallelhyflex.parsing.ParsingException;
import parallelhyflex.parsing.grammar.OperatorBinder;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenStreamParser;
import parallelhyflex.problems.fdcsp.problem.constraints.EqualConstraint;
import parallelhyflex.problems.fdcsp.problem.constraints.GreaterThanConstraint;
import parallelhyflex.problems.fdcsp.problem.constraints.GreaterThanOrEqualConstraint;
import parallelhyflex.problems.fdcsp.problem.constraints.MinimizingOperator;
import parallelhyflex.problems.fdcsp.problem.constraints.NotEqualConstraint;
import parallelhyflex.problems.fdcsp.problem.constraints.SmallerThanConstraint;
import parallelhyflex.problems.fdcsp.problem.constraints.SmallerThanOrEqualConstraint;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblemParser {
    
    private static TokenStreamParser<Token> tokenParser;
    private static VariableTokenGenerator variableToken = new VariableTokenGenerator();
    
    public static TokenStreamParser getTokenParser() {
        if (tokenParser == null) {
            tokenParser = new TokenStreamParser();
            tokenParser.addToken(new FiniteIntegerDomain());
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
    
    public FDCOPProblem parse(InputStream stream) throws ParsingException {
        OperatorBinder ob = new OperatorBinder();
        Iterable<Token> tokens = ob.bind(getTokenParser().getIterable(stream));
        ArrayList<FDCOPConstraint> constraints = new ArrayList<>();
        for (Token t : tokens) {
            if (t instanceof FDCOPConstraint) {
                constraints.add((FDCOPConstraint) t);
            }
        }
        VariableStore vs = variableToken.getVariableStore();
        Variable[] vars = new Variable[vs.size()];
        int i = 0;
        for (Variable v : vs) {
            vars[i++] = v;
        }
        return new FDCOPProblem(vars, constraints.toArray(new FDCOPConstraint[0]), null);
    }
    
    public FDCOPProblem parse(String text) throws IOException, ParsingException {
        InputStream is = new ByteArrayInputStream(text.getBytes());
        FDCOPProblem result = parse(is);
        is.close();
        return result;
    }
}
