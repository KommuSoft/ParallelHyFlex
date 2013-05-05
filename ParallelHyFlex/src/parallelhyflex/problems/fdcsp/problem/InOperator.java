package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.parsing.TokenAnnotation;
import parallelhyflex.parsing.TokenBase;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "in")
@OperatorAnnotation()
public class InOperator extends TokenBase<InOperator> {

    private static final InOperator instance = new InOperator();

    public static InOperator getInstance() {
        return instance;
    }

    @Override
    public InOperator generate(String variable) {
        return this;
    }
}
