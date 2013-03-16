package parallelhyflex.problems.dummy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import parallelhyflex.problemdependent.Solution;

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

    @Override
    public void write(DataOutputStream os) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void read(DataInputStream is) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean hasFastDifferenceWith (DummySolution other) {
        return false;
    }
    
}
