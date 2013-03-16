package parallelhyflex.problemdependent;

import java.io.Serializable;
import parallelhyflex.communication.ReadWriteable;

/**
 *
 * @author kommusoft
 */
public interface Solution<TSolution> extends Serializable, ReadWriteable {
    
    public TSolution clone ();
    public boolean equalSolution (TSolution other);
    public boolean hasFastDifferenceWith (TSolution other);
    
}
