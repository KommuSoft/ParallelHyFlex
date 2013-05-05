package parallelhyflex.parsing;

import java.util.regex.Pattern;


public abstract class TokenBase<T> implements Token<T> {
    
    private Pattern pattern = null;
    
    public Pattern getPattern () {
        if(this.pattern == null) {
            TokenAnnotation ta = this.getClass().getAnnotation((Class<TokenAnnotation>) TokenAnnotation.class);
            this.pattern = Pattern.compile(ta.token());
        }
        return this.pattern;
    }
    
}
