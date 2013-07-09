package parallelhyflex.parsing;

import parallelhyflex.utils.ExceptionBase;

/**
 *
 * @author kommusoft
 */
public class ParsingException extends ExceptionBase {

    /**
     *
     * @param message
     */
    public ParsingException(String message) {
        super(message);
    }
    
    /**
     *
     * @param message
     * @param parameters
     */
    public ParsingException(String message, Object... parameters) {
        super(message,parameters);
    }
    
}
