package parallelhyflex.problemdependent.searchspace;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface SearchSpace<TSolution extends Solution<TSolution>> {
    
    public void correct (TSolution solution);
    public boolean isInSearchSpace (TSolution solution);
    public boolean isNotInSearchSpace (TSolution solution);
    
}
