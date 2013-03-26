package parallelhyflex.problemdependent.searchspace;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class DummySearchSpace<TSolution extends Solution<TSolution>> extends SearchSpaceBase<TSolution> {

    @Override
    public void correct(TSolution solution) {
    }

    @Override
    public boolean isInSearchSpace(TSolution solution) {
        return true;
    }
}
