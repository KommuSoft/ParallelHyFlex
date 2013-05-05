package parallelhyflex.problems.fdcsp.problem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import parallelhyflex.parsing.ParsingException;
import parallelhyflex.parsing.grammar.OperatorBinder;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenParser;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblemParser {
    
    private static TokenParser<Token> tokenParser;
    
    public static TokenParser getTokenParser () {
        if(tokenParser == null) {
            tokenParser = new TokenParser();
            tokenParser.addToken(new FiniteIntegerDomain());
            tokenParser.addToken(new InOperator());
            tokenParser.addToken(new Variable());
        }
        return tokenParser;
    }
    
    public FDCOPProblemParser parse (InputStream stream) throws ParsingException {
        LinkedList<Object> tokenStream = new LinkedList<>();
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
