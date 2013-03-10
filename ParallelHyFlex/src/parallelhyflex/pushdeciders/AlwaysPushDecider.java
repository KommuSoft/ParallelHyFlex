package parallelhyflex.pushdeciders;

import parallelhyflex.Solution;

/**
 *
 * @author kommusoft
 */
public class AlwaysPushDecider<TSolution extends Solution<TSolution>> implements PushDecider<TSolution> {

    @Override
    public boolean decidePush(int index, TSolution sol) {
        return true;
    }
    
}
