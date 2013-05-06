package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.problems.fdcsp.problem.constraints.InOperator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    
    public static TokenStreamParser getTokenParser () {
        if(tokenParser == null) {
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
    
    public FDCOPProblemParser parse (InputStream stream) throws ParsingException {
        OperatorBinder ob = new OperatorBinder();
        ob.bind(getTokenParser().getIterable(stream));
        return null;
    }
    public FDCOPProblemParser parse (String text) throws IOException, ParsingException {
        InputStream is = new ByteArrayInputStream(text.getBytes());
        FDCOPProblemParser result = parse(is);
        is.close();
        return result;
    }
    
}
