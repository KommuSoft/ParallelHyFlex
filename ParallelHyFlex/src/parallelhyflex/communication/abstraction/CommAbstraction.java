package parallelhyflex.communication.abstraction;

import mpi.Datatype;
import mpi.Op;

/**
 *
 * @author kommusoft
 */
public interface CommAbstraction extends CommModeSensitive {

    RequestResult Allgather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException;

    RequestResult Allgatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype) throws NotSupportedByCommModeException;

    RequestResult Allreduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op) throws NotSupportedByCommModeException;

    RequestResult Alltoall(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype) throws NotSupportedByCommModeException;

    RequestResult Alltoallv(Object sendbuf, int sendoffset, int[] sendcount, int[] sdispls, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] rdispls, Datatype recvtype) throws NotSupportedByCommModeException;

    RequestResult Barrier() throws NotSupportedByCommModeException;

    RequestResult Bcast(Object buf, int offset, int count, Datatype type, int root) throws NotSupportedByCommModeException;

    RequestResult Gather(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException;

    RequestResult Gatherv(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int[] recvcount, int[] displs, Datatype recvtype, int root) throws NotSupportedByCommModeException;

    RequestResult Reduce(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int count, Datatype datatype, Op op, int root) throws NotSupportedByCommModeException;

    RequestResult Reduce_scatter(Object sendbuf, int sendoffset, Object recvbuf, int recvoffset, int[] recvcounts, Datatype datatype, Op op) throws NotSupportedByCommModeException;

    RequestResult Scatter(Object sendbuf, int sendoffset, int sendcount, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException;

    RequestResult Scatterv(Object sendbuf, int sendoffset, int[] sendcount, int[] displs, Datatype sendtype, Object recvbuf, int recvoffset, int recvcount, Datatype recvtype, int root) throws NotSupportedByCommModeException;

    RequestResult Recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) throws NotSupportedByCommModeException;

    RequestResult Send(Object buf, int offset, int count, Datatype datatype, int dest, int tag) throws NotSupportedByCommModeException;
}
