package parallelhyflex.parsing.grammar;

import java.util.LinkedList;
import parallelhyflex.parsing.tokenizing.Token;

/**
 *
 * @author kommusoft
 */
public class OperatorBinder {
    
    private LinkedList<Token> bind (Iterable<? extends Token> tokenStream) {
        LinkedList<Token> memory = new LinkedList<>();
        for(Token t : tokenStream) {
            memory.add(t);
        }
        return memory;
    }
    
}
