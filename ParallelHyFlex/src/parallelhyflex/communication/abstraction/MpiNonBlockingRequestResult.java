package parallelhyflex.communication.abstraction;

import mpi.Status;

public class MpiNonBlockingRequestResult implements RequestResult {

    private static final MpiNonBlockingRequestResult singleInstance = new MpiNonBlockingRequestResult();

    static MpiNonBlockingRequestResult getInstance() {
        return singleInstance;
    }
    private final mpi.Request innerRequest;

    private MpiNonBlockingRequestResult() {
        this(null);
    }

    public MpiNonBlockingRequestResult(mpi.Request innerRequest) {
        this.innerRequest = innerRequest;
    }

    @Override
    public Status Wait() {
        return this.innerRequest.Wait();
    }

    @Override
    public Status Test() {
        return this.innerRequest.Test();
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.MpiNonBlocking;
    }
}
