package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface IArgumentCloneable<TArgument,TDestination> {
    
    TDestination clone (TArgument argument);
    
}
