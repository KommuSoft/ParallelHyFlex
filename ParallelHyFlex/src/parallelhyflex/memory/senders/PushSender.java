package parallelhyflex.memory.senders;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface PushSender<TSolution extends Solution<TSolution>> {

    public void sendSolution(int index, TSolution solution);
}
