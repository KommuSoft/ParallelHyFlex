package parallelhyflex.communication.abstraction;

import parallelhyflex.utils.ExceptionBase;


public class NotSupportedByCommModeException extends ExceptionBase {

    public NotSupportedByCommModeException() {
    }

    public NotSupportedByCommModeException(String message) {
        super(message);
    }

    public NotSupportedByCommModeException(String message, Object... parameters) {
        super(message, parameters);
    }
    
}
