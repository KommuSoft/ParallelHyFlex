package parallelhyflex.memory;

import parallelhyflex.memory.deciders.AlwaysPushDecider;
import parallelhyflex.memory.deciders.IthPushDecider;
import parallelhyflex.memory.deciders.ProbablePushDecider;
import parallelhyflex.memory.senders.BroadcastPushSender;
import parallelhyflex.memory.senders.DistributedPushSender;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public enum MemoryExchangePolicy {

    QueuedAlwaysBroadcasted {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedAlwaysBroadcasted);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedAlwaysBroadcasted, new AlwaysPushDecider<TSol>(), new BroadcastPushSender<TSol>());
        }
    },
    QueuedAlwaysDistributed {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedAlwaysDistributed);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedAlwaysDistributed, new AlwaysPushDecider<TSol>(), new DistributedPushSender<TSol>());
        }
    },
    QueuedProbableBroadcasted {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedProbableBroadcasted);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedProbableBroadcasted, new ProbablePushDecider<TSol>(), new BroadcastPushSender<TSol>());
        }
    },
    QueuedProbableDistributed {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedProbableDistributed);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedProbableDistributed, new ProbablePushDecider<TSol>(), new DistributedPushSender<TSol>());
        }
    },
    QueuedIthBroadcasted {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedIthBroadcasted);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedIthBroadcasted, new IthPushDecider<TSol>(memorySize, 5), new BroadcastPushSender<TSol>());
        }
    },
    QueuedIthDistributed {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedIthDistributed);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.QueuedIthDistributed, new IthPushDecider<TSol>(memorySize, 5), new DistributedPushSender<TSol>());
        }
    },
    StateAlwaysBroadcasted {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new StateProxyMemorySlots<>(memorySize, MemoryExchangePolicy.StateAlwaysBroadcasted);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.StateAlwaysBroadcasted, new AlwaysPushDecider<TSol>(), new BroadcastPushSender<TSol>());
        }
    },
    StateAlwaysDistributed {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new StateProxyMemorySlots<>(memorySize, MemoryExchangePolicy.StateAlwaysDistributed);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.StateAlwaysDistributed, new AlwaysPushDecider<TSol>(), new DistributedPushSender<TSol>());
        }
    },
    StateProbableBroadcasted {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new StateProxyMemorySlots<>(memorySize, MemoryExchangePolicy.StateProbableBroadcasted);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.StateProbableBroadcasted, new ProbablePushDecider<TSol>(), new BroadcastPushSender<TSol>());
        }
    },
    StateProbableDistributed {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new StateProxyMemorySlots<>(memorySize, MemoryExchangePolicy.StateProbableDistributed);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.StateProbableDistributed, new ProbablePushDecider<TSol>(), new DistributedPushSender<TSol>());
        }
    },
    StateIthBroadcasted {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new StateProxyMemorySlots<>(memorySize, MemoryExchangePolicy.StateIthBroadcasted);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.StateIthBroadcasted, new IthPushDecider<TSol>(memorySize, 5), new BroadcastPushSender<TSol>());
        }
    },
    StateIthDistributed {
        @Override
        public <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize) {
            return new StateProxyMemorySlots<>(memorySize, MemoryExchangePolicy.StateIthDistributed);
        }

        @Override
        public <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize) {
            return new LocalMemorySlots<>(memorySize, MemoryExchangePolicy.StateIthDistributed, new IthPushDecider<TSol>(memorySize, 5), new DistributedPushSender<TSol>());
        }
    };

    abstract <TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver(int memorySize);

    abstract <TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender(int memorySize);
}
