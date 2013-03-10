package parallelhyflex.pushdeciders;

import parallelhyflex.Solution;

/**
 *
 * @author kommusoft
 */
public interface PushDecider<TSolution extends Solution<TSolution>> {
    
    public boolean decidePush (int index, TSolution sol);
    
}
