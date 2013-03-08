package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public abstract class DistanceFunction<TSolution extends Solution> {
    
    public abstract double evaluateDistance (TSolution solution1, TSolution solution2);
    
}
