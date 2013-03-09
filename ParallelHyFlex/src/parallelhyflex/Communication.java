package parallelhyflex;

import mpi.*;

/**
 *
 * @author kommusoft
 */
public class Communication {
    
    private static Communication MainCommunication;
    private static int[] other;
    
    public static void initializeCommunication (String[] args) {
        MainCommunication = new Communication(args);
    }
    public static int[] others () {
        return other;
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
        other = new int[size-1];
        for(int i = 0; i < rank; i++) {
            other[i] = i;
        }
        for(int i = rank+1; i < size; i++) {
            other[i-1] = i;
        }
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
        Log("AG");
        MPI.COMM_WORLD.Allgather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
    }
    public static void BC (Object buf, int offset, int count, Datatype type, int root) {
        Log("BC");
        MPI.COMM_WORLD.Bcast(buf, offset, count, type, root);
    }
    public static Status RV (Object buf, int offset, int count, Datatype type, int source, int tag) {
        Log("RV");
        return MPI.COMM_WORLD.Recv(buf, offset, count, type, source, tag);
    }
    public static Request NbRV (Object buf, int offset, int count, Datatype type, int source, int tag) {
        Log("NbRV");
        return MPI.COMM_WORLD.Irecv(buf, offset, count, type, source, tag);
    }
    public static void S (Object buf, int offset, int count, Datatype type, int dest, int tag) {
        Log("S");
        MPI.COMM_WORLD.Send(buf, offset, count, type, dest, tag);
    }
    public static Request NbS (Object buf, int offset, int count, Datatype type, int dest, int tag) {
        Log("NbS");
        return MPI.COMM_WORLD.Isend(buf, offset, count, type, dest, tag);
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
