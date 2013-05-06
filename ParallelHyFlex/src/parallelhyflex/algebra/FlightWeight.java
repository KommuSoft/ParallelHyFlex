package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface FlightWeight<TOrigin, Type> extends Generator<TOrigin, Type>, Iterable<Type> {

    Generator<TOrigin, Type> getGenerator();

    boolean inCache(TOrigin origin);

    void reduce();
    
    int size ();
    
    void clear ();
}
