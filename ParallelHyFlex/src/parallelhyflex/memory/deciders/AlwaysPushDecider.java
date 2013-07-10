package parallelhyflex.memory.deciders;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class AlwaysPushDecider<TSolution extends Solution<TSolution>> implements PushDecider<TSolution> {

    /**
     *
     * @param index
     * @param sol
     * @return
     */
    @Override
    public boolean decidePush(int index, TSolution sol) {
        return true;
    }
    private static final Logger LOG = Logger.getLogger(AlwaysPushDecider.class.getName());
}
