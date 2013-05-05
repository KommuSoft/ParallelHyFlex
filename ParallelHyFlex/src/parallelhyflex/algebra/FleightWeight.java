package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface FleightWeight<TOrigin, Type> extends Generator<TOrigin, Type> {

    Generator<TOrigin, Type> getGenerator();

    boolean inCache(TOrigin origin);

    void reduce();
}
