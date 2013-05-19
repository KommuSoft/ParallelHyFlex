package parallelhyflex.communication.abstraction;

/**
 *
 * @author kommusoft
 */
public enum CommMode {

    MpiBlocking {
        @Override
        public boolean isBlocking() {
            return true;
        }

        @Override
        public boolean isReliable() {
            return true;
        }

        @Override
        public MpiBlockingComm getAbstraction() {
            return MpiBlockingComm.getInstance();
        }
    },
    MpiNonBlocking {
        @Override
        public boolean isBlocking() {
            return false;
        }

        @Override
        public boolean isReliable() {
            return true;
        }

        @Override
        public MpiNonBlockingComm getAbstraction() {
            return MpiNonBlockingComm.getInstance();
        }
    },
    UdpBlocking {
        @Override
        public boolean isBlocking() {
            return true;
        }

        @Override
        public boolean isReliable() {
            return false;
        }

        @Override
        public CommAbstraction getAbstraction() {
            return null;
        }
    },
    UdpNonBlocking {
        @Override
        public boolean isBlocking() {
            return false;
        }

        @Override
        public boolean isReliable() {
            return false;
        }

        @Override
        public UdpNonBlockingComm getAbstraction() {
            return UdpNonBlockingComm.getInstance();
        }
    };

    public abstract boolean isBlocking();

    public abstract boolean isReliable();

    public abstract CommAbstraction getAbstraction();
}
