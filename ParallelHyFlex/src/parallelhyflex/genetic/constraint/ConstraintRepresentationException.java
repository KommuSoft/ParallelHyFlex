package parallelhyflex.genetic.constraint;

import parallelhyflex.utils.ExceptionBase;

/**
 *
 * @author kommusoft
 */
public class ConstraintRepresentationException extends ExceptionBase {

    public ConstraintRepresentationException() {
    }

    public ConstraintRepresentationException(String message) {
        super(message);
    }

    public ConstraintRepresentationException(String message, Object... parameters) {
        super(message, parameters);
    }
}
