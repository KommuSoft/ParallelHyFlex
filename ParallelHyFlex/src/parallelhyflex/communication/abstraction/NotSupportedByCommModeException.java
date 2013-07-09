package parallelhyflex.communication.abstraction;

import parallelhyflex.utils.ExceptionBase;


/**
 *
 * @author kommusoft
 */
public class NotSupportedByCommModeException extends ExceptionBase {

    /**
     *
     */
    public NotSupportedByCommModeException() {
    }

    /**
     *
     * @param message
     */
    public NotSupportedByCommModeException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param parameters
     */
    public NotSupportedByCommModeException(String message, Object... parameters) {
        super(message, parameters);
    }
    
}
