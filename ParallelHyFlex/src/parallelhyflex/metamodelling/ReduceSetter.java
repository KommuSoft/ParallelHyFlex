package parallelhyflex.metamodelling;

import parallelhyflex.algebra.ReduceOperator;

/**
 *
 * @author kommusoft
 */
public @interface ReduceSetter {
    
    public String name();
    public ReduceOperator operator ();
    
}
