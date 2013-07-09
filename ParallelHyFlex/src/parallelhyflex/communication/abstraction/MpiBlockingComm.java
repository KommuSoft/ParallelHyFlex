package parallelhyflex.communication.abstraction;

import mpi.Datatype;
import mpi.MPI;
import mpi.Op;

/**
 *
 * @author kommusoft
 */
public class MpiBlockingComm implements CommAbstraction {

    private static final MpiBlockingComm singleInstance = new MpiBlockingComm();

    /**
     *
     * @return
     */
    public static MpiBlockingComm getInstance() {
        return singleInstance;
    }

    private MpiBlockingComm() {
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
     */
    @Override
    public MpiBlockingRequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Allgather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) {
        MPI.COMM_WORLD.Allgatherv(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, displs, recvtype);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) {
        MPI.COMM_WORLD.Allreduce(sendbuf, sendoffset, recvbuf, recvoffset, count, datatype, op);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Alltoall(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) {
        MPI.COMM_WORLD.Alltoallv(sendbuf, sendoffset, sendcount, sdispls, sendtype, recvbuf, recvoffset, recvcount, rdispls, recvtype);
        return MpiBlockingRequestResult.getInstance();
    }

    /**
     *
     * @return
     */
    @Override
    public MpiBlockingRequestResult Barrier() {
        MPI.COMM_WORLD.Barrier();
        return MpiBlockingRequestResult.getInstance();
    }

    /**
     *
     * @param buf
     * @param offset
     * @param count
     * @param type
     * @param root
     * @return
     */
    @Override
    public MpiBlockingRequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) {
        MPI.COMM_WORLD.Bcast(buf, offset, count, type, root);
        return MpiBlockingRequestResult.getInstance();
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
    public MpiBlockingRequestResult BcastRoot(Object buf, int offset, int count, Datatype type, int tag) throws NotSupportedByCommModeException {
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
     */
    @Override
    public MpiBlockingRequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Gather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Gatherv(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, displs, recvtype, root);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) {
        MPI.COMM_WORLD.Reduce_scatter(sendbuf, sendoffset, recvbuf, recvoffset, recvcounts, datatype, op);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) {
        MPI.COMM_WORLD.Reduce(sendbuf, sendoffset, recvbuf, recvoffset, count, datatype, op, root);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Scatter(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        return MpiBlockingRequestResult.getInstance();
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
     */
    @Override
    public MpiBlockingRequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Scatterv(sendbuf, sendoffset, sendcount, displs, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        return MpiBlockingRequestResult.getInstance();
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
    public MpiBlockingRequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) {
        return new MpiBlockingRequestResult(MPI.COMM_WORLD.Recv(buf, offset, count, datatype, source, tag));
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
    public MpiBlockingRequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) {
        MPI.COMM_WORLD.Send(buf, offset, count, datatype, dest, tag);
        return MpiBlockingRequestResult.getInstance();
    }

    /**
     *
     * @return
     */
    @Override
    public CommMode getCommMode() {
        return CommMode.MpiBlocking;
    }
}
