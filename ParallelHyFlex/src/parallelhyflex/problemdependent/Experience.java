package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface Experience<TSolution extends Solution<TSolution>> {
    
    public void join (TSolution solution);
    
}
