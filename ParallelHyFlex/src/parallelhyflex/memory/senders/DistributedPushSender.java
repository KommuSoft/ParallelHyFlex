package parallelhyflex.memory.senders;

import java.util.logging.Logger;
import mpi.MPI;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.abstraction.CommMode;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class DistributedPushSender<TSolution extends Solution<TSolution>> extends PushSenderBase<TSolution> {

    private int sendIndex = 0;

    /**
     *
     * @param index
     * @param solution
     */
    @Override
    public void sendSolution(int index, TSolution solution) {
        if (Communication.getCommunication().getSize() > 1) {
            Object[] data = generatePacket(index, solution);
            Communication.Send(CommMode.MpiNonBlocking, data, 0, 1, MPI.OBJECT, Communication.others()[this.sendIndex], PushSenderBase.SendTag);
            this.sendIndex = (this.sendIndex + 1) % (Communication.getCommunication().getSize() - 1);
        }
    }
    private static final Logger LOG = Logger.getLogger(DistributedPushSender.class.getName());
}
