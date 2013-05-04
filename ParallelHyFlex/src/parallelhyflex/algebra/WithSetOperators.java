package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface WithSetOperators<Q,T> {
    
    Q union (T other) throws InductiveBiasException;
    void unionWith (T other) throws InductiveBiasException;
    Q intersection (T other) throws InductiveBiasException;
    void intersectWith (T other) throws InductiveBiasException;
    Q minus (T other) throws InductiveBiasException;
    void minusWith (T other) throws InductiveBiasException;
    
}
