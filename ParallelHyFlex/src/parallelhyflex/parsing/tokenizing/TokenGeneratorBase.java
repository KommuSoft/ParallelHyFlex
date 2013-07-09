package parallelhyflex.parsing.tokenizing;

import java.util.regex.Pattern;

/**
 *
 * @author kommusoft
 * @param <T>
 */
public abstract class TokenGeneratorBase<T extends Token> implements TokenGenerator<T> {

    /**
     *
     * @param text
     * @return
     */
    @Override
    public boolean validate(String text) {
        return TokenGeneratorImplementation.validate(this, text);
    }

    /**
     *
     * @return
     */
    @Override
    public double getPriority() {
        return TokenGeneratorImplementation.getPriority(this);
    }

    /**
     *
     * @return
     */
    @Override
    public Pattern getPattern() {
        return TokenGeneratorImplementation.getPattern(this);
    }
}
