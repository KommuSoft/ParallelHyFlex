package parallelhyflex.parsing;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public interface Token<T> extends Generator<String, T> {

    boolean validate(String text);

    double getPriority();
}
