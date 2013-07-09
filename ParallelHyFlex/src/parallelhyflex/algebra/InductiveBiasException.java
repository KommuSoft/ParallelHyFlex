package parallelhyflex.algebra;

import parallelhyflex.utils.ExceptionBase;

/**
 *
 * @author kommusoft
 */
public class InductiveBiasException extends ExceptionBase {

    /**
     *
     */
    public InductiveBiasException() {
        super();
    }

    /**
     *
     * @param message
     */
    public InductiveBiasException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param parameters
     */
    public InductiveBiasException(String message, Object... parameters) {
        super(message, parameters);
    }
}
