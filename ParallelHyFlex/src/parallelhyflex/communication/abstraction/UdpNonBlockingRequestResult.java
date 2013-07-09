package parallelhyflex.communication.abstraction;

import mpi.Status;

/**
 *
 * @author kommusoft
 */
public class UdpNonBlockingRequestResult implements RequestResult {

    private static final UdpNonBlockingRequestResult singleInstance = new UdpNonBlockingRequestResult();

    /**
     *
     * @return
     */
    public static UdpNonBlockingRequestResult getInstance() {
        return singleInstance;
    }

    /**
     *
     */
    public UdpNonBlockingRequestResult() {
    }

    /**
     *
     * @return
     */
    @Override
    public Status Wait() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Status Test() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public CommMode getCommMode() {
        return CommMode.UdpNonBlocking;
    }
}