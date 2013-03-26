package parallelhyflex.memory.senders;

import mpi.MPI;
import parallelhyflex.communication.Communication;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class BroadcastPushSender<TSolution extends Solution<TSolution>> extends PushSenderBase<TSolution> {

    @Override
    public void sendSolution(int index, TSolution solution) {
        Object[] data = this.generatePacket(index, solution);
        for (int other : Communication.others()) {
            Communication.NbS(data, 0, 3, MPI.OBJECT, other, 0);
        }
    }
}
