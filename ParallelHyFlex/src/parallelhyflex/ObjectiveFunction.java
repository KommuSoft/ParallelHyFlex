package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public interface ObjectiveFunction<TSolution extends Solution> {
    
    public double evaluateSolution (TSolution solution);
    
}
