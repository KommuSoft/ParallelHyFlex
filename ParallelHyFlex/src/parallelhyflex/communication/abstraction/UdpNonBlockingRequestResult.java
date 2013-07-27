package parallelhyflex.communication.abstraction;

import java.util.logging.Logger;
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
    private static final Logger LOG = Logger.getLogger(UdpNonBlockingRequestResult.class.getName());
}