package parallelhyflex.parsing.grammar;

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
public @interface OperatorAnnotation {

    double operatorPriority() default 0.0d;

    OperatorType operatorType() default OperatorType.BindBoth;
    
    boolean associative () default false;
}
