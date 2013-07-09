package parallelhyflex.communication.abstraction;

import mpi.Datatype;
import mpi.MPI;
import mpi.Op;
import parallelhyflex.communication.Communication;

/**
 *
 * @author kommusoft
 */
public class MpiNonBlockingComm implements CommAbstraction {
    
    private static final MpiNonBlockingComm singleInstance  = new MpiNonBlockingComm();
    
    /**
     *
     * @return
     */
    public static MpiNonBlockingComm getInstance () {
        return singleInstance;
    }
    
    private MpiNonBlockingComm () {}

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
    public MpiNonBlockingRequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    /**
     *
     * @return
     * @throws NotSupportedByCommModeException
     */
    @Override
    public MpiNonBlockingRequestResult Barrier() throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
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
    public MpiNonBlockingRequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
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
     */
    @Override
    public MpiNonBlockingRequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) {
        return new MpiNonBlockingRequestResult(MPI.COMM_WORLD.Irecv(buf, offset, count, datatype, source, tag));
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
     */
    @Override
    public MpiNonBlockingRequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) {
        return new MpiNonBlockingRequestResult(MPI.COMM_WORLD.Isend(buf, offset, count, datatype, dest, tag));
    }
    
    /**
     *
     * @param buf
     * @param offset
     * @param count
     * @param type
     * @param tag
     * @return
     */
    @Override
    public MpiNonBlockingRequestResult BcastRoot(Object buf, int offset, int count, Datatype type, int tag) {
        for (int root : Communication.others()) {
            this.Send(buf, offset, count, type, root, tag);
        }
        return MpiNonBlockingRequestResult.getInstance();
    }

    /**
     *
     * @return
     */
    @Override
    public CommMode getCommMode() {
        return CommMode.MpiNonBlocking;
    }
}
