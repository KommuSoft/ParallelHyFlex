package parallelhyflex.memory.deciders;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class IthPushDecider<TSolution extends Solution<TSolution>> implements PushDecider<TSolution> {

    private int[] counters;
    private int n;

    /**
     *
     * @param memorySize
     * @param n
     */
    public IthPushDecider(int memorySize, int n) {
        this.n = n;
        this.counters = new int[memorySize];
    }

    /**
     *
     * @param index
     * @param sol
     * @return
     */
    @Override
    public boolean decidePush(int index, TSolution sol) {
        int counter = this.counters[index];
        counter++;
        boolean result = counter >= this.n;
        if (result) {
            counter = 0;
        }
        this.counters[index] = counter;
        return result;
    }
    private static final Logger LOG = Logger.getLogger(IthPushDecider.class.getName());
}
