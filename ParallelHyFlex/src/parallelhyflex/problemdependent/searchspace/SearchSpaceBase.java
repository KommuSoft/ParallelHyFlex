package parallelhyflex.problemdependent.searchspace;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class SearchSpaceBase<TSolution extends Solution<TSolution>> implements SearchSpace<TSolution> {
    
    @Override
    public boolean isNotInSearchSpace(TSolution solution) {
        return !this.isInSearchSpace(solution);
    }
    
}
