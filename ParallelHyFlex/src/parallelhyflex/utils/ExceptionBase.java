package parallelhyflex.utils;

/**
 *
 * @author kommusoft
 */
public abstract class ExceptionBase extends Exception {

    protected ExceptionBase() {
        super();
    }

    protected ExceptionBase(String message) {
        super(message);
    }

    protected ExceptionBase(String message, Object... parameters) {
        this(String.format(message, parameters));
    }
}
