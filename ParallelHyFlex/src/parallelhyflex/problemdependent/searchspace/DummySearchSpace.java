package parallelhyflex.problemdependent.searchspace;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class DummySearchSpace<TSolution extends Solution<TSolution>> extends SearchSpaceBase<TSolution> {

    /**
     *
     * @param solution
     */
    @Override
    public void correct(TSolution solution) {
    }

    /**
     *
     * @param solution
     * @return
     */
    @Override
    public boolean isInSearchSpace(TSolution solution) {
        return true;
    }
}
