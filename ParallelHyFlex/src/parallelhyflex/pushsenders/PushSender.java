package parallelhyflex.pushsenders;

import parallelhyflex.problemdependent.Solution;

/**
 *
 * @author kommusoft
 */
public interface PushSender<TSolution extends Solution<TSolution>> {
    
    public void sendSolution (int index, TSolution solution);
    
}
