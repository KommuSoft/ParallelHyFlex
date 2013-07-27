package parallelhyflex.communication.abstraction;

import java.util.logging.Logger;
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
    private static final Logger LOG = Logger.getLogger(NotSupportedByCommModeException.class.getName());
    
}
