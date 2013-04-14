package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface ArgumentCloneable<TArgument, TDestination> {

    TDestination clone(TArgument argument);
}
