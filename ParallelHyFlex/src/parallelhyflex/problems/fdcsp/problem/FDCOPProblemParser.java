package parallelhyflex.problems.fdcsp.problem;

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
        }
        return tokenParser;
    }
    
}
