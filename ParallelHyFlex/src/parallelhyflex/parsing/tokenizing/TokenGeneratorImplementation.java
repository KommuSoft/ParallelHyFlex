package parallelhyflex.parsing.tokenizing;

import java.util.regex.Pattern;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.WeakFleightWeight;

/**
 *
 * @author kommusoft
 */
public class TokenGeneratorImplementation implements Generator<Class, Pattern> {

    private static final WeakFleightWeight<Class, Pattern> patternFleightWeight = new WeakFleightWeight<>(new TokenGeneratorImplementation());

    /**
     *
     * @param tokenGenerator
     * @param text
     * @return
     */
    public static boolean validate(TokenGenerator tokenGenerator, String text) {
        return tokenGenerator.getPattern().matcher(text).matches();
    }

    /**
     *
     * @param tokenGenerator
     * @return
     */
    public static double getPriority(TokenGenerator tokenGenerator) {
        return tokenGenerator.getClass().getAnnotation((Class<TokenAnnotation>) TokenAnnotation.class).parsePriority();
    }

    /**
     *
     * @param tokenGenerator
     * @return
     */
    public static Pattern getPattern(TokenGenerator tokenGenerator) {
        return patternFleightWeight.generate(tokenGenerator.getClass());
    }

    private TokenGeneratorImplementation() {
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public Pattern generate(Class variable) {
        return Pattern.compile(((TokenAnnotation) variable.getAnnotation((Class<TokenAnnotation>) TokenAnnotation.class)).token());
    }
}