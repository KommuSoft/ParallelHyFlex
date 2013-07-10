package parallelhyflex.memory.deciders;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ProbablePushDecider<TSolution extends Solution<TSolution>> implements PushDecider<TSolution> {

    private double probability;

    /**
     *
     */
    public ProbablePushDecider() {
        this(0.5);
    }

    /**
     *
     * @param probability
     */
    public ProbablePushDecider(double probability) {
        this.probability = probability;
    }

    /**
     *
     * @param index
     * @param sol
     * @return
     */
    @Override
    public boolean decidePush(int index, TSolution sol) {
        return Utils.StaticRandom.nextDouble() < probability;
    }
    private static final Logger LOG = Logger.getLogger(ProbablePushDecider.class.getName());
}
