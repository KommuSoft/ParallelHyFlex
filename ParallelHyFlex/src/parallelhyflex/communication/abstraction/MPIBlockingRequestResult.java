package parallelhyflex.communication.abstraction;

import mpi.Status;

public class MPIBlockingRequestResult implements RequestResult {

    private final mpi.Status innerStatus;
    private static final mpi.Status singleStatusInstance = new mpi.Status();
    private static final MPIBlockingRequestResult singleInstance = new MPIBlockingRequestResult();

    private MPIBlockingRequestResult () {
        this(singleStatusInstance);
    }
    public MPIBlockingRequestResult(mpi.Status innerStatus) {
        this.innerStatus = innerStatus;
    }

    @Override
    public Status Wait() {
        return this.innerStatus;
    }

    @Override
    public Status Test() {
        return this.innerStatus;
    }
    
    public static MPIBlockingRequestResult getInstance () {
        return singleInstance;
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.MPIBlocking;
    }
    
}
