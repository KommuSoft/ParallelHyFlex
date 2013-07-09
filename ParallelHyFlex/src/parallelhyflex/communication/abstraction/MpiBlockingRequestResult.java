package parallelhyflex.communication.abstraction;

import mpi.Status;

/**
 *
 * @author kommusoft
 */
public class MpiBlockingRequestResult implements RequestResult {
    private static final mpi.Status singleStatusInstance = new mpi.Status();
    private static final MpiBlockingRequestResult singleInstance = new MpiBlockingRequestResult();

    /**
     *
     * @return
     */
    public static MpiBlockingRequestResult getInstance () {
        return singleInstance;
    }

    private final mpi.Status innerStatus;
    private MpiBlockingRequestResult() {
        this(singleStatusInstance);
    }

        /**
     *
     * @param innerStatus
     */
    public MpiBlockingRequestResult(mpi.Status innerStatus) {
        this.innerStatus = innerStatus;
    }

    /**
     *
     * @return
     */
    @Override
    public Status Wait() {
        return this.innerStatus;
    }

    /**
     *
     * @return
     */
    @Override
    public Status Test () {
        return this.innerStatus;
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
