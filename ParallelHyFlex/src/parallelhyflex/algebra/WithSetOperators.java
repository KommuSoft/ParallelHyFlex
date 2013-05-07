package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface WithSetOperators<Q, T> {

    Q union(T other) throws InductiveBiasException;

    boolean unionWith(T other) throws InductiveBiasException;

    Q intersection(T other) throws InductiveBiasException;

    boolean intersectWith(T other) throws InductiveBiasException;

    Q minus(T other) throws InductiveBiasException;

    boolean minusWith(T other) throws InductiveBiasException;

    boolean canIntersect(T tr);

    boolean canMinus(T tr);

    boolean canUnion(T si);
}
