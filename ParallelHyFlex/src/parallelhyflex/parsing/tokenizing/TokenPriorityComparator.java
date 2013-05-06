package parallelhyflex.parsing.tokenizing;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 */
public class TokenPriorityComparator implements Comparator<TokenGenerator> {
    
    private static final TokenPriorityComparator instance = new TokenPriorityComparator();
    
    public static TokenPriorityComparator getInstance () {
        return instance;
    }

    @Override
    public int compare(TokenGenerator t1, TokenGenerator t2) {
        int cp = ((Double) t1.getPriority()).compareTo(t2.getPriority());
        if(cp == 0x00) {
            return t1.getClass().getCanonicalName().compareTo(t2.getClass().getCanonicalName());
        }
        else {
            return cp;
        }
    }
    
}
