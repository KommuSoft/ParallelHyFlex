package parallelhyflex.problems.fdcsp.problem;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import parallelhyflex.parsing.Token;
import parallelhyflex.parsing.TokenParser;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblemParser {
    
    private static TokenParser tokenParser;
    
    public static TokenParser getTokenParser () {
        if(tokenParser == null) {
            tokenParser = new TokenParser();
            tokenParser.addToken(new DomainToken());
            tokenParser.addToken(new InOperator());
            tokenParser.addToken(new Variable());
        }
        return tokenParser;
    }
    
    public FDCOPProblemParser parse (InputStream stream) {
        LinkedList<Object> tokenStream = new LinkedList<>();
        for(Object t : getTokenParser().getIterable(stream)) {
            tokenStream.add(t);
            System.out.println(t);
        }
        return null;
    }
    public FDCOPProblemParser parse (String text) throws IOException {
        InputStream is = new ByteArrayInputStream(text.getBytes());
        FDCOPProblemParser result = parse(is);
        is.close();
        return result;
    }
    
}
