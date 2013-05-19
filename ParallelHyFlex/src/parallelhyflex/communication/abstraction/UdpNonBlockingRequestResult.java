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
    public Status Wait() throws Exception {
        return null;
    }
    
    @Override
    public Status Test () throws Exception {
        return null;
    }

    @Override
    public CommMode getCommMode() {
        return CommMode.UdpNonBlocking;
    }
}