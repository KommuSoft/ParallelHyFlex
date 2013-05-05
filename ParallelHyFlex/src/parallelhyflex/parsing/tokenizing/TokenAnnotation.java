package parallelhyflex.parsing.tokenizing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author kommusoft
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenAnnotation {
    
    String token();
    double parsePriority() default 1.0d;
    
}
