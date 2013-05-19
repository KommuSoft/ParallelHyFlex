package parallelhyflex.communication.abstraction;

import mpi.Status;

public class UDPNonBlockingRequestResult implements RequestResult {
    
    private static final UDPNonBlockingRequestResult singleInstance = new UDPNonBlockingRequestResult();

    public UDPNonBlockingRequestResult() {
    }

    @Override
    public Status Wait() throws Exception {
        return null;
    }

    @Override
    public Status Test() throws Exception {
        return null;
    }
    
    public static UDPNonBlockingRequestResult getInstance () {
        return singleInstance;
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.UDPNonBlocking;
    }
}