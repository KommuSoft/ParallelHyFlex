package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface DistanceFunction<TSolution extends Solution<TSolution>> {
    
    public double evaluateDistance (TSolution solution1, TSolution solution2);
    public boolean evaluateDistanceSmallerThanOrEqual (TSolution solution1, TSolution solution2, double maxDistance);
    
}
