package parallelhyflex.memory.deciders;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class IthPushDecider<TSolution extends Solution<TSolution>> implements PushDecider<TSolution> {

    private int[] counters;
    private int n;

    public IthPushDecider(int memorySize, int n) {
        this.n = n;
        this.counters = new int[memorySize];
    }

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
}
