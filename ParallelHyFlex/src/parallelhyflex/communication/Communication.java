package parallelhyflex.communication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpi.Datatype;
import mpi.MPI;
import mpi.Op;
import parallelhyflex.communication.abstraction.CommMode;
import parallelhyflex.communication.abstraction.NotSupportedByCommModeException;
import parallelhyflex.communication.abstraction.RequestResult;
import parallelhyflex.communication.abstraction.UdpWorld;
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

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void initializeCommunication(String[] args) throws IOException {
        MainCommunication = new Communication(args);
        logFile = new File("log" + MainCommunication.rank + ".txt");
        logWriter = new BufferedWriter(new FileWriter(logFile));
        logFileTime(LoggingParameters.LOG_START, LoggingParameters.LOG_START_TEXT);
    }

    /**
     *
     * @return
     */
    public static int[] others() {
        return other;
    }

    /**
     *
     * @return
     */
    public static Communication getCommunication() {
        return MainCommunication;
    }

    /**
     *
     * @throws IOException
     */
    public static void finalizeCommunication() throws IOException {
        logWriter.close();
        Communication comm = MainCommunication;
        comm.finalize();
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @return
     */
    public static RequestResult Allgather(CommMode mode, Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        try {
            return mode.getAbstraction().Allgather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param displs
     * @param recvtype
     * @return
     */
    public static RequestResult Allgatherv(CommMode mode, Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) {
        try {
            return mode.getAbstraction().Allgatherv(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, displs, recvtype);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param recvbuf
     * @param recvoffset
     * @param count
     * @param datatype
     * @param op
     * @return
     */
    public static RequestResult Allreduce(CommMode mode, Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) {
        try {
            return mode.getAbstraction().Allreduce(sendbuf, sendoffset, recvbuf, recvoffset, count, datatype, op);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @return
     */
    public static RequestResult Alltoall(CommMode mode, Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        try {
            return mode.getAbstraction().Alltoall(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sdispls
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param rdispls
     * @param recvtype
     * @return
     */
    public static RequestResult Alltoallv(CommMode mode, Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) {
        try {
            return mode.getAbstraction().Alltoallv(sendbuf, sendoffset, sendcount, sdispls, sendtype, recvbuf, recvoffset, recvcount, rdispls, recvtype);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @return
     */
    public static RequestResult Barrier(CommMode mode) {
        try {
            return mode.getAbstraction().Barrier();
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param buf
     * @param offset
     * @param count
     * @param type
     * @param root
     * @return
     */
    public static RequestResult Bcast(CommMode mode, Object buf, int offset, int count, Datatype type, int root) {
        try {
            return mode.getAbstraction().Bcast(buf, offset, count, type, root);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param buf
     * @param offset
     * @param count
     * @param type
     * @param tag
     * @return
     */
    public static RequestResult BcastRoot(CommMode mode, Object buf, int offset, int count, Datatype type, int tag) {
        try {
            return mode.getAbstraction().BcastRoot(buf, offset, count, type, tag);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @param root
     * @return
     */
    public static RequestResult Gather(CommMode mode, Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        try {
            return mode.getAbstraction().Gather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param displs
     * @param recvtype
     * @param root
     * @return
     */
    public static RequestResult Gatherv(CommMode mode, Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) {
        try {
            return mode.getAbstraction().Gatherv(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, displs, recvtype, root);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param recvbuf
     * @param recvoffset
     * @param recvcounts
     * @param datatype
     * @param op
     * @return
     */
    public static RequestResult Reduce_scatter(CommMode mode, Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) {
        try {
            return mode.getAbstraction().Reduce_scatter(sendbuf, sendoffset, recvbuf, recvoffset, recvcounts, datatype, op);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param recvbuf
     * @param recvoffset
     * @param count
     * @param datatype
     * @param op
     * @param root
     * @return
     */
    public static RequestResult Reduce(CommMode mode, Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) {
        try {
            return mode.getAbstraction().Reduce(sendbuf, sendoffset, recvbuf, recvoffset, count, datatype, op, root);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @param root
     * @return
     */
    public static RequestResult Scatter(CommMode mode, Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        try {
            return mode.getAbstraction().Scatter(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param displs
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @param root
     * @return
     */
    public static RequestResult Scatterv(CommMode mode, Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        try {
            return mode.getAbstraction().Scatterv(sendbuf, sendoffset, sendcount, displs, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param buf
     * @param offset
     * @param count
     * @param datatype
     * @param source
     * @param tag
     * @return
     */
    public static RequestResult Recv(CommMode mode, Object buf, int offset, int count, Datatype datatype, int source, int tag) {
        try {
            return mode.getAbstraction().Recv(buf, offset, count, datatype, source, tag);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param mode
     * @param buf
     * @param offset
     * @param count
     * @param datatype
     * @param dest
     * @param tag
     * @return
     */
    public static RequestResult Send(CommMode mode, Object buf, int offset, int count, Datatype datatype, int dest, int tag) {
        try {
            return mode.getAbstraction().Send(buf, offset, count, datatype, dest, tag);
        } catch (NotSupportedByCommModeException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param message
     */
    public static void log(String message) {
        System.out.println("<" + Communication.MainCommunication.rank + "> " + message);
    }

    /**
     *
     * @param object
     */
    public static <T> void log(Iterable<T> list) {
        log(Utils.toString(list));
    }

    /**
     *
     * @param object
     */
    public static <T> void log(T... list) {
        log(Utils.toString(list));
    }

    /**
     *
     * @param object
     */
    public static void log(Object object) {
        log(String.valueOf(object));
    }

    /**
     *
     * @param format
     * @param args
     */
    public static void log(String format, Object... args) {
        System.out.println("<" + Communication.MainCommunication.rank + "> " + String.format(format, args));
    }

    /**
     *
     * @param locale
     * @param format
     * @param args
     */
    public static void log(Locale locale, String format, Object... args) {
        System.out.println("<" + Communication.MainCommunication.rank + "> " + String.format(locale, format, args));
    }

    /**
     *
     * @param message
     */
    public static void logFile(String message) {
        try {
            logWriter.write(message);
            logWriter.newLine();
        } catch (Exception e) {
        }
    }

    /**
     *
     * @param format
     * @param args
     */
    public static void logFileTime(String format, Object... args) {
        logFileTime(String.format(format, args));
    }

    /**
     *
     * @param locale
     * @param format
     * @param args
     */
    public static void logFileTime(Locale locale, String format, Object... args) {
        logFileTime(String.format(locale, format, args));
    }

    /**
     *
     * @param message
     */
    public static void logFileTime(String message) {
        try {
            logWriter.write(String.format("%s\t%s\n", new Date().getTime(), message));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param condition
     * @param format
     * @param args
     */
    public static void logFileTime(boolean condition, String format, Object... args) {
        if (condition) {
            logFileTime(format, args);
        }
    }

    /**
     *
     * @param condition
     * @param locale
     * @param format
     * @param args
     */
    public static void logFileTime(boolean condition, Locale locale, String format, Object... args) {
        if (condition) {
            logFileTime(locale, format, args);
        }
    }

    /**
     *
     * @param condition
     * @param message
     */
    public static void logFileTime(boolean condition, String message) {
        if (condition) {
            logFileTime(message);
        }
    }

    /**
     *
     * @param e
     */
    public static void log(Exception e) {
        log("ERROR: " + e.toString());
        e.printStackTrace();
    }
    private final int rank;
    private final int size;
    private final int dim;
    private final int nonNeighborCache;
    private final UdpWorld udpWorld;

    private Communication(String[] args) throws SocketException, UnknownHostException {
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
        int d = Utils.ceiling2Log(tmp);
        int nc = 0;
        for (int i = 0; i < d; i++) {
            int l = 1 << i;
            if ((rank ^ l) >= size) {
                nc |= 1 << i;
            }
        }
        this.nonNeighborCache = nc;
        dim = d;
        this.udpWorld = new UdpWorld(rank);
    }

    /**
     *
     */
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

    /**
     *
     * @return
     */
    public int getDimensions() {
        return dim;
    }

    /**
     *
     * @param dimension
     * @return
     */
    public int getDimensionId(int dimension) {
        return ((this.rank >> dimension) & 0x01);
    }

    /**
     *
     * @param dimension
     * @return
     */
    public boolean hasNeighbor(int dimension) {
        int neighbor = this.getRank() ^ (0x01 << dimension);
        return (neighbor < this.getSize());
    }

    /**
     *
     * @param dimension
     * @return
     */
    public int getNeighbor(int dimension) {
        int rank = this.getRank();
        int neighbor = rank ^ (0x01 << dimension);
        if (neighbor < this.getSize()) {
            return neighbor;
        } else {
            return rank;
        }
    }

    /**
     *
     * @param neighbor
     * @return
     */
    public int getDimensionDifference(int neighbor) {
        int diff = this.getRank() ^ neighbor;
        int d = 0;
        while (diff != 0 && (diff & 1) != 1) {
            diff >>= 1;
            d++;
        }
        if (diff != 0) {
            return d;
        } else {
            return -1;
        }
    }

    /**
     * @return the nonNeighborCache
     */
    public int getNonNeighborCache() {
        return nonNeighborCache;
    }

    /**
     *
     * @return
     */
    public boolean isHalfWall() {
        int fullsize = 1 << dim;
        return rank >= (fullsize >> 1) && size < fullsize;
    }

    /**
     *
     * @return
     */
    public boolean isFullWall() {
        return !this.isHalfWall();
    }

    /**
     *
     * @return
     */
    public boolean areAssymetricWalls() {
        return (1 << this.dim) != this.size;
    }

    /**
     *
     * @return
     */
    public int getWallNeighbor() {
        return rank ^ (1 << (dim - 1));
    }

    /**
     *
     * @return
     */
    public boolean hasWallNeighbor() {
        return this.getWallNeighbor() < size;
    }
    private static final Logger LOG = Logger.getLogger(Communication.class.getName());
}
