package parallelhyflex.communication.abstraction;

import java.util.logging.Logger;
import mpi.Datatype;
import mpi.Op;

/**
 *
 * @author kommusoft
 */
public class UdpNonBlockingComm implements CommAbstraction {

    private static final UdpNonBlockingComm singleInstance = new UdpNonBlockingComm();

    /**
     *
     * @return
     */
    public static UdpNonBlockingComm getInstance() {
        return singleInstance;
    }

    private UdpNonBlockingComm() {
    }

    /**
     *
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
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
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param sendbuf
     * @param sendoffset
     * @param recvbuf
     * @param recvoffset
     * @param count
     * @param datatype
     * @param op
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param sendbuf
     * @param sendoffset
     * @param sendcount
     * @param sendtype
     * @param recvbuf
     * @param recvoffset
     * @param recvcount
     * @param recvtype
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
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
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Barrier() throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param buf
     * @param offset
     * @param count
     * @param type
     * @param root
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
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
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
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
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param sendbuf
     * @param sendoffset
     * @param recvbuf
     * @param recvoffset
     * @param count
     * @param datatype
     * @param op
     * @param root
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param sendbuf
     * @param sendoffset
     * @param recvbuf
     * @param recvoffset
     * @param recvcounts
     * @param datatype
     * @param op
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
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
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
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
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param buf
     * @param offset
     * @param count
     * @param datatype
     * @param source
     * @param tag
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @param buf
     * @param offset
     * @param count
     * @param datatype
     * @param dest
     * @param tag
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public UdpNonBlockingRequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @return
     */
    @Override
    public CommMode getCommMode() {
        return CommMode.UdpNonBlocking;
    }

    /**
     *
     * @param buf
     * @param offset
     * @param count
     * @param type
     * @param tag
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public RequestResult BcastRoot(Object buf, int offset, int count, Datatype type, int tag) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }
    private static final Logger LOG = Logger.getLogger(UdpNonBlockingComm.class.getName());
}
