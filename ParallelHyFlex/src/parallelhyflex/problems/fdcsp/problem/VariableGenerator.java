package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class VariableGenerator implements Generator<String,Variable> {
    
    private static final VariableGenerator instance = new VariableGenerator();
    
    /**
     *
     * @return
     */
    public static VariableGenerator getInstance () {
        return instance;
    }
    
    /**
     *
     * @param variable
     * @return
     */
    @Override
    public Variable generate(String variable) {
        return new Variable(variable);
    }
    
}
