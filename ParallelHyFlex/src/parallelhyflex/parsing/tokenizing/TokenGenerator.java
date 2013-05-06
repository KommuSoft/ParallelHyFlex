package parallelhyflex.parsing.tokenizing;

import java.util.regex.Pattern;
import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public interface TokenGenerator<T extends Token> extends Generator<String, T> {

    boolean validate(String text);

    double getPriority();

    Pattern getPattern();
}
