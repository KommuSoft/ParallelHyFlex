package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface WithAddSubNegOperators<Q,T> {
    
    Q add (T other) throws InductiveBiasException;
    
    void addWith (T other) throws InductiveBiasException;
    
    Q sub (T other) throws InductiveBiasException;
    
    void subWith (T other) throws InductiveBiasException;
    
    Q neg () throws InductiveBiasException;
    
    void negWith () throws InductiveBiasException;
    
    boolean canAdd (T other);
    
    boolean canSub (T other);
    
    boolean canNeg ();
    
}
