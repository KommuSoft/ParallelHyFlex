package parallelhyflex.problems.fdcsp.problem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parallelhyflex.parsing.TokenAnnotation;
import parallelhyflex.parsing.TokenBase;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "(\\[(-?[0-9]+),(-?[0-9]+)\\]|\\{(-?[0-9]+)\\})(u(\\[(-?[0-9]+),(-?[0-9]+)\\]|\\{(-?[0-9]+)\\}))*")
public class DomainToken extends TokenBase<FiniteIntegerDomain> {

    private Pattern subPattern = null;

    private Pattern getSubPattern() {
        if (subPattern == null) {
            this.subPattern = Pattern.compile("\\[(-?[0-9]+),(-?[0-9]+)\\]|\\{(-?[0-9]+)\\}");
        }
        return this.subPattern;
    }

    @Override
    public FiniteIntegerDomain generate(String text) {
        Matcher matcher = this.getPattern().matcher(text);
        if (matcher.matches()) {
            FiniteIntegerDomain fid = new FiniteIntegerDomain();
            matcher = this.getSubPattern().matcher(text);
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    fid.add(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                } else {
                    fid.add(Integer.parseInt(matcher.group(3)));
                }
            }
            return fid;
        } else {
            return null;
        }
    }
}
