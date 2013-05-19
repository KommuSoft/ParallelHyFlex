package parallelhyflex.communication.abstraction;

import mpi.Status;

public class UdpNonBlockingRequestResult implements RequestResult {

    private static final UdpNonBlockingRequestResult singleInstance = new UdpNonBlockingRequestResult();

    public static UdpNonBlockingRequestResult getInstance() {
        return singleInstance;
    }

    public UdpNonBlockingRequestResult() {
    }

    @Override
    public Status Wait() {
        return null;
    }

    @Override
    public Status Test() {
        return null;
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.UdpNonBlocking;
    }
}