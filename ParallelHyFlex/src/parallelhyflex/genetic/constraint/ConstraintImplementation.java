package parallelhyflex.genetic.constraint;

/**
 *
 * @author kommusoft
 */
public interface ConstraintImplementation<TConstraint> {
    
    public TConstraint generateConstraint (int[] values);
    
    public boolean Satisfied (int[] values);
    
}
