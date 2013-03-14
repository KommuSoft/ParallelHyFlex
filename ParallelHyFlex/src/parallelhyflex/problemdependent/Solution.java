package parallelhyflex.problemdependent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author kommusoft
 */
public interface Solution<TSolution> extends Serializable {
    
    public TSolution clone ();
    public boolean equalSolution (TSolution other);
    public void writeSolution (DataOutputStream os) throws IOException;
    public void readSolution (DataInputStream is) throws IOException;
    public boolean hasFastDifferenceWith (TSolution other);
    
}
