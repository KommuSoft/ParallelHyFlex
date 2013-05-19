package parallelhyflex.communication.abstraction;

import mpi.Status;

public class MpiBlockingRequestResult implements RequestResult {
    private static final mpi.Status singleStatusInstance = new mpi.Status();
    private static final MpiBlockingRequestResult singleInstance = new MpiBlockingRequestResult();

    public static MpiBlockingRequestResult getInstance () {
        return singleInstance;
    }

    private final mpi.Status innerStatus;
    private MpiBlockingRequestResult() {
        this(singleStatusInstance);
    }

        public MpiBlockingRequestResult(mpi.Status innerStatus) {
        this.innerStatus = innerStatus;
    }

    @Override
    public Status Wait() {
        return this.innerStatus;
    }

    @Override
    public Status Test () {
        return this.innerStatus;
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.MpiBlocking;
    }
    
}
