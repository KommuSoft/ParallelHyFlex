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
    
    public static void A2A (Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Alltoall(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
    }
    public static void AG (Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Allgather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
    }
    public static void BC (Object buf, int offset, int count, Datatype type, int root) {
        MPI.COMM_WORLD.Bcast(buf, offset, count, type, root);
    }
    public static void Log (String message) {
        System.out.println("<"+Communication.MainCommunication.rank+"> "+message);
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
