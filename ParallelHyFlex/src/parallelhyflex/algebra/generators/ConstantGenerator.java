package parallelhyflex.algebra.generators;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class ConstantGenerator<TX,TF> implements Generator<TX,TF> {
    
    private final TF constant;
    
    public ConstantGenerator (TF constant) {
        this.constant = constant;   
    }

    @Override
    public TF generate(TX variable) {
        return constant;
    }

    /**
     * @return the constant
     */
    public TF getConstant() {
        return constant;
    }
    
}
