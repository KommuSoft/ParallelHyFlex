package parallelhyflex.parsing;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 */
public class TokenPriorityComparator implements Comparator<Token> {
    
    private static final TokenPriorityComparator instance = new TokenPriorityComparator();
    
    public static TokenPriorityComparator getInstance () {
        return instance;
    }

    @Override
    public int compare(Token t1, Token t2) {
        return ((Double) t1.getPriority()).compareTo(t2.getPriority());
    }
    
}
