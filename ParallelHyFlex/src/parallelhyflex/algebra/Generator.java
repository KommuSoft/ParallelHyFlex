package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface Generator<TToGenerate,TFromVariable> {
    
    TToGenerate generate (TFromVariable variable);
    
}
