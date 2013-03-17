package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public interface SearchSpace<TSolution extends Solution<TSolution>> {
    
    public void correct (TSolution solution);
    public boolean isInSearchSpace (TSolution solution);
    public boolean isNotInSearchSpace (TSolution solution);
    
}
