package parallelhyflex.communication.abstraction;

import mpi.Datatype;
import mpi.Op;

public class UdpNonBlockingComm implements CommAbstraction {

    private static final UdpNonBlockingComm singleInstance = new UdpNonBlockingComm();

    public static UdpNonBlockingComm getInstance() {
        return singleInstance;
    }

    private UdpNonBlockingComm() {
    }

    @Override
    public UdpNonBlockingRequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Barrier() throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public UdpNonBlockingRequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.UdpNonBlocking;
    }

    @Override
    public RequestResult BcastRoot(Object buf, int offset, int count, Datatype type, int tag) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }
}
