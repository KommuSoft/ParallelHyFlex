package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class FDCOPObjectiveFunctionIGenerator implements Generator<Integer, FDCOPObjectiveFunctionI> {
    
    private static final FDCOPObjectiveFunctionIGenerator instance = new FDCOPObjectiveFunctionIGenerator();
    
    public static FDCOPObjectiveFunctionIGenerator getInstance () {
        return instance;
    }
    
    private FDCOPObjectiveFunctionIGenerator () {}

    @Override
    public FDCOPObjectiveFunctionI generate(Integer variable) {
        return new FDCOPObjectiveFunctionI(variable);
    }
}
