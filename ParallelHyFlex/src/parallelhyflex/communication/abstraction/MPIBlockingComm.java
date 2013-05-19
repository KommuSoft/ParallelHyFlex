package parallelhyflex.communication.abstraction;

import mpi.Datatype;
import mpi.MPI;
import mpi.Op;

public class MPIBlockingComm implements CommAbstraction {

    @Override
    public MPIBlockingRequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Allgather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) {
        MPI.COMM_WORLD.Allgatherv(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, displs, recvtype);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) {
        MPI.COMM_WORLD.Allreduce(sendbuf, sendoffset, recvbuf, recvoffset, count, datatype, op);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) {
        MPI.COMM_WORLD.Alltoall(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) {
        MPI.COMM_WORLD.Alltoallv(sendbuf, sendoffset, sendcount, sdispls, sendtype, recvbuf, recvoffset, recvcount, rdispls, recvtype);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Barrier() {
        MPI.COMM_WORLD.Barrier();
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) {
        MPI.COMM_WORLD.Bcast(buf, offset, count, type, root);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Gather(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Gatherv(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, displs, recvtype, root);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) {
        MPI.COMM_WORLD.Reduce_scatter(sendbuf, sendoffset, recvbuf, recvoffset, recvcounts, datatype, op);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) {
        MPI.COMM_WORLD.Reduce(sendbuf, sendoffset, recvbuf, recvoffset, count, datatype, op, root);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Scatter(sendbuf, sendoffset, sendcount, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) {
        MPI.COMM_WORLD.Scatterv(sendbuf, sendoffset, sendcount, displs, sendtype, recvbuf, recvoffset, recvcount, recvtype, root);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public MPIBlockingRequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) {
        return new MPIBlockingRequestResult(MPI.COMM_WORLD.Recv(buf, offset, count, datatype, source, tag));
    }

    @Override
    public MPIBlockingRequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) {
        MPI.COMM_WORLD.Send(buf, offset, count, datatype, dest, tag);
        return MPIBlockingRequestResult.getInstance();
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.MPIBlocking;
    }
}
