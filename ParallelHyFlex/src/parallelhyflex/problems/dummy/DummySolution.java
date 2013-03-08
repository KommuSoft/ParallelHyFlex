package parallelhyflex.problems.dummy;

import parallelhyflex.Solution;

/**
 *
 * @author kommusoft
 */
public class DummySolution implements Solution<DummySolution> {

    @Override
    public DummySolution clone() {
        return new DummySolution();
    }

    @Override
    public boolean equalSolution(DummySolution other) {
        return true;
    }
    
}
