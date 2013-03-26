package parallelhyflex.memory.deciders;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface PushDecider<TSolution extends Solution<TSolution>> {

    public boolean decidePush(int index, TSolution sol);
}
