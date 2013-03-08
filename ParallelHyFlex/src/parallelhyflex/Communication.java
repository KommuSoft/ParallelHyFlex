package parallelhyflex;

import mpi.*;

/**
 *
 * @author kommusoft
 */
public class Communication {
    
    private static Communication MainCommunication;
    
    public static void initializeCommunication (String[] args) {
        MainCommunication = new Communication(args);
    }
    
    public static Communication getCommunication () {
        return MainCommunication;
    }
    
    private final int rank;
    private final int size;
    
    private Communication (String[] args) {
        MPI.Init(args);
        rank = MPI.COMM_WORLD.Rank();
        size = MPI.COMM_WORLD.Size();
    }
    
    @Override
    public void finalize () {
        MPI.Finalize();
    }
    
    public static void finalizeCommunication () {
        MainCommunication.finalize();
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
}
