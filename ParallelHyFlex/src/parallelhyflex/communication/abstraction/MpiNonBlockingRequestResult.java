package parallelhyflex.communication.abstraction;

import mpi.Status;

public class MpiNonBlockingRequestResult implements RequestResult {

    private final mpi.Request innerRequest;

    public MpiNonBlockingRequestResult(mpi.Request innerRequest) {
        this.innerRequest = innerRequest;
    }

    @Override
    public Status Wait() throws Exception {
        return this.innerRequest.Wait();
    }

    @Override
    public Status Test() throws Exception {
        return this.innerRequest.Test();
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.MpiNonBlocking;
    }
}
