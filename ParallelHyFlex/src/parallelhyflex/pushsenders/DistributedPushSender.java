package parallelhyflex.pushsenders;

import mpi.MPI;
import parallelhyflex.Communication;
import parallelhyflex.Solution;

/**
 *
 * @author kommusoft
 */
public class DistributedPushSender<TSolution extends Solution<TSolution>> implements PushSender<TSolution> {
    
    private int sendIndex = 0;
    
    @Override
    public void sendSolution(int index, TSolution solution) {
        Object[] data = new Object[3];
        data[0] = Communication.getCommunication().getRank();
        data[1] = index;
        data[2] = solution;
        Communication.NbS(data, 0, 3, MPI.OBJECT, Communication.others()[this.sendIndex], 0);
        this.sendIndex = (this.sendIndex+1)%(Communication.getCommunication().getSize()-1);
    }
    
}
