package parallelhyflex.communication.abstraction;

import mpi.Status;

/**
 *
 * @author kommusoft
 */
public class MpiNonBlockingRequestResult implements RequestResult {

    private static final MpiNonBlockingRequestResult singleInstance = new MpiNonBlockingRequestResult();

    static MpiNonBlockingRequestResult getInstance() {
        return singleInstance;
    }
    private final mpi.Request innerRequest;

    private MpiNonBlockingRequestResult() {
        this(null);
    }

    /**
     *
     * @param innerRequest
     */
    public MpiNonBlockingRequestResult(mpi.Request innerRequest) {
        this.innerRequest = innerRequest;
    }

    /**
     *
     * @return
     */
    @Override
    public Status Wait() {
        return this.innerRequest.Wait();
    }

    /**
     *
     * @return
     */
    @Override
    public Status Test() {
        return this.innerRequest.Test();
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
