package parallelhyflex.utils;

/**
 *
 * @author kommusoft
 */
public abstract class ExceptionBase extends Exception {

    /**
     *
     */
    protected ExceptionBase() {
        super();
    }

    /**
     *
     * @param message
     */
    protected ExceptionBase(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param parameters
     */
    protected ExceptionBase(String message, Object... parameters) {
        this(String.format(message, parameters));
    }
}
