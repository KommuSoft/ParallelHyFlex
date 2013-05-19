package parallelhyflex.communication.abstraction;

import mpi.Datatype;
import mpi.MPI;
import mpi.Op;

public class MPINonBlockingComm implements CommAbstraction {

    @Override
    public MPINonBlockingRequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Barrier() throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException {
        throw new NotSupportedByCommModeException();
    }

    @Override
    public MPINonBlockingRequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) {
        return new MPINonBlockingRequestResult(MPI.COMM_WORLD.Irecv(buf, offset, count, datatype, source, tag));
    }

    @Override
    public MPINonBlockingRequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) {
        return new MPINonBlockingRequestResult(MPI.COMM_WORLD.Isend(buf, offset, count, datatype, dest, tag));
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.MPINonBlocking;
    }
}
