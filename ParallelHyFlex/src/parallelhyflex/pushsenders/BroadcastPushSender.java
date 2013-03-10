package parallelhyflex.pushsenders;

import mpi.MPI;
import parallelhyflex.Communication;
import parallelhyflex.Solution;

/**
 *
 * @author kommusoft
 */
public class BroadcastPushSender<TSolution extends Solution<TSolution>> implements PushSender<TSolution> {

    @Override
    public void sendSolution(int index, TSolution solution) {
        Object[] data = new Object[3];
        data[0] = Communication.getCommunication().getRank();
        data[1] = index;
        data[2] = solution;
        for(int other : Communication.others()) {
            Communication.NbS(data, 0, 3, MPI.OBJECT, other, 0);
        }
    }
    
}
