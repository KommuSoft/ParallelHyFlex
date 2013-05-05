package parallelhyflex.parsing;

import java.util.regex.Pattern;

public abstract class TokenBase<T> implements Token<T> {

    private Pattern pattern = null;

    @Override
    public boolean validate(String text) {
        return this.getPattern().matcher(text).matches();
    }

    @Override
    public double getPriority() {
        return this.getClass().getAnnotation((Class<TokenAnnotation>) TokenAnnotation.class).parsePriority();
    }

    public Pattern getPattern() {
        if (this.pattern == null) {
            TokenAnnotation ta = this.getClass().getAnnotation((Class<TokenAnnotation>) TokenAnnotation.class);
            this.pattern = Pattern.compile(ta.token());
        }
        return this.pattern;
    }
}
