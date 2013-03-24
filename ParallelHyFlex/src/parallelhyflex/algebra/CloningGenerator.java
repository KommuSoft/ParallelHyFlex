package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public class CloningGenerator<TArgument,TDestination extends IArgumentCloneable<? super TArgument,TDestination>> implements Generator<TArgument,TDestination> {
    
    private final TDestination origin;
    
    public CloningGenerator (TDestination origin) {
        this.origin = origin;
    }

    @Override
    public TDestination generate(TArgument variable) {
        return this.getOrigin().clone(variable);
    }

    /**
     * @return the origin
     */
    public TDestination getOrigin() {
        return origin;
    }
    
}
