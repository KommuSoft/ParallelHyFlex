package parallelhyflex;

import java.io.Serializable;

/**
 *
 * @author kommusoft
 */
public interface Solution<TSolution> extends Serializable {
    
    public TSolution clone ();
    public boolean equalSolution (TSolution other);
    
}
