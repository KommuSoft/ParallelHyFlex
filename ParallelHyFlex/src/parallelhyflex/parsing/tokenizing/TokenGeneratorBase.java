package parallelhyflex.parsing.tokenizing;

import java.util.regex.Pattern;

public abstract class TokenGeneratorBase<T extends Token> implements TokenGenerator<T> {

    @Override
    public boolean validate(String text) {
        return TokenGeneratorImplementation.validate(this, text);
    }

    @Override
    public double getPriority() {
        return TokenGeneratorImplementation.getPriority(this);
    }

    @Override
    public Pattern getPattern() {
        return TokenGeneratorImplementation.getPattern(this);
    }
}
