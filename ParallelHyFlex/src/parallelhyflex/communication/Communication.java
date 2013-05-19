package parallelhyflex.communication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import mpi.*;
import parallelhyflex.logging.LoggingParameters;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class Communication {

    private static Communication MainCommunication;
    private static int[] other;
    private static File logFile;
    private static BufferedWriter logWriter;

    public static void initializeCommunication(String[] args) throws IOException {
        MainCommunication = new Communication(args);
        logFile = new File("log" + MainCommunication.rank + ".txt");
        logWriter = new BufferedWriter(new FileWriter(logFile));
        logFileTime(LoggingParameters.LOG_START, LoggingParameters.LOG_START_TEXT);
    }

    public static int[] others() {
        return other;
    }

    public static Communication getCommunication() {
        return MainCommunication;
    }

    public static void finalizeCommunication() throws IOException {
        logWriter.close();
        Communication comm = MainCommunication;
        comm.finalize();
    }

    public static void a2A(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Alltoall(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
    }

    public static void aG(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Allgather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
    }

    public static void bC(Object buf, int offset, int count, Datatype type, int root) {
        MPI.COMM_WORLD.Bcast(buf, offset, count, type, root);
    }

    public static Status rV(Object buf, int offset, int count, Datatype type, int source, int tag) {
        return MPI.COMM_WORLD.Recv(buf, offset, count, type, source, tag);
    }

    public static Request nbRv(Object buf, int offset, int count, Datatype type, int source, int tag) {
        return MPI.COMM_WORLD.Irecv(buf, offset, count, type, source, tag);
    }

    public static void s(Object buf, int offset, int count, Datatype type, int dest, int tag) {
        MPI.COMM_WORLD.Send(buf, offset, count, type, dest, tag);
    }

    public static Request nbS(Object buf, int offset, int count, Datatype type, int dest, int tag) {
        return MPI.COMM_WORLD.Isend(buf, offset, count, type, dest, tag);
    }

    public static void nbB(Object buf, int offset, int count, Datatype type, int tag) {
        for (int root : Communication.others()) {
            Communication.nbS(buf, offset, count, type, root, tag);
        }
    }

    public static void log(String message) {
        System.out.println("<" + Communication.MainCommunication.rank + "> " + message);
    }

    public static void logFile(String message) {
        try {
            logWriter.write(message);
            logWriter.newLine();
        } catch (Exception e) {
        }
    }

    public static void logFileTime(String format, Object... args) {
        logFileTime(String.format(format, args));
    }

    public static void logFileTime(Locale locale, String format, Object... args) {
        logFileTime(String.format(locale, format, args));
    }

    public static void logFileTime(String message) {
        try {
            logWriter.write(String.format("%s\t%s\n", new Date().getTime(), message));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void logFileTime(boolean condition, String format, Object... args) {
        if (condition) {
            logFileTime(format, args);
        }
    }

    public static void logFileTime(boolean condition, Locale locale, String format, Object... args) {
        if (condition) {
            logFileTime(locale, format, args);
        }
    }

    public static void logFileTime(boolean condition, String message) {
        if (condition) {
            logFileTime(message);
        }
    }

    public static void log(Exception e) {
        log("ERROR: " + e.toString());
    }
    private final int rank;
    private final int size;
    private final int dim;
    private final int nonNeighborCache;

    private Communication(String[] args) {
        MPI.Init(args);
        rank = MPI.COMM_WORLD.Rank();
        size = MPI.COMM_WORLD.Size();
        other = new int[size - 1];
        for (int i = 0; i < rank; i++) {
            other[i] = i;
        }
        for (int i = rank + 1; i < size; i++) {
            other[i - 1] = i;
        }
        int tmp = size - 1;
        int d = Utils.priority2Log(tmp);
        int nc = 0;
        for(int i = 0; i < d; i++) {
            int l = 1<<i;
            if((rank^l) >= size) {
                nc |= 1<<i;
            }
        }
        this.nonNeighborCache = nc;
        dim = d;
    }

    @Override
    public void finalize() {
        MPI.Finalize();
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

    public int getDimensions() {
        return dim;
    }

    public int getDimensionId(int dimension) {
        return ((this.rank >> dimension) & 0x01);
    }

    public boolean hasNeighbor(int dimension) {
        int neighbor = this.getRank() ^ (0x01 << dimension);
        return (neighbor < this.getSize());
    }

    public int getNeighbor(int dimension) {
        int rank = this.getRank();
        int neighbor = rank ^ (0x01 << dimension);
        if (neighbor < this.getSize()) {
            return neighbor;
        } else {
            return rank;
        }
    }
    
    public int getDimensionDifference(int neighbor) {
        int diff = this.getRank()^neighbor;
        int d = 0;
        while(diff != 0 && (diff&1) != 1) {
            diff >>= 1;
            d++;
        }
        if(diff != 0) {
            return d;
        }
        else {
            return -1;
        }
    }

    /**
     * @return the nonNeighborCache
     */
    public int getNonNeighborCache() {
        return nonNeighborCache;
    }

}
