package parallelhyflex.parsing;

import parallelhyflex.utils.ExceptionBase;

/**
 *
 * @author kommusoft
 */
public class ParsingException extends ExceptionBase {

    public ParsingException(String message) {
        super(message);
    }
    
    public ParsingException(String message, Object... parameters) {
        super(message,parameters);
    }
    
}
