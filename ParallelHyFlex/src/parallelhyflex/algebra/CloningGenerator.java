package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public class CloningGenerator<TArgument, TDestination extends ArgumentCloneable<? super TArgument, TDestination>> implements Generator<TArgument, TDestination> {

    private final TDestination origin;

    /**
     *
     * @param origin
     */
    public CloningGenerator(TDestination origin) {
        this.origin = origin;
    }

    /**
     *
     * @param variable
     * @return
     */
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
