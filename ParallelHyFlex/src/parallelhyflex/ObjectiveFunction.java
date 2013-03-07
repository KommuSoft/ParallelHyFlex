package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public abstract class ObjectiveFunction<TSolution extends Solution> {
    
    public abstract double evaluateSolution (TSolution solution);
    
}
