package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface Generator<TFromVariable, TToGenerate> {

    TToGenerate generate(TFromVariable variable);
}
