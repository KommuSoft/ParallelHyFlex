package parallelhyflex.memory;

import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.pushdeciders.AlwaysPushDecider;
import parallelhyflex.pushdeciders.IthPushDecider;
import parallelhyflex.pushdeciders.ProbablePushDecider;
import parallelhyflex.pushsenders.BroadcastPushSender;
import parallelhyflex.pushsenders.DistributedPushSender;

/**
 *
 * @author kommusoft
 */
public enum MemoryExchangePolicy {
    
    QueuedAlwaysBroadcasted {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.QueuedAlwaysBroadcasted);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.QueuedAlwaysBroadcasted,new AlwaysPushDecider<TSol>(),new BroadcastPushSender<TSol>());
        }
        
    },
    
    QueuedAlwaysDistributed {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.QueuedAlwaysDistributed);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.QueuedAlwaysDistributed,new AlwaysPushDecider<TSol>(),new DistributedPushSender<TSol>());
        }
        
    },
    
    QueuedProbableBroadcasted {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.QueuedProbableBroadcasted);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.QueuedProbableBroadcasted,new ProbablePushDecider<TSol>(),new BroadcastPushSender<TSol>());
        }
        
    },
    
    QueuedProbableDistributed {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.QueuedProbableDistributed);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.QueuedProbableDistributed,new ProbablePushDecider<TSol>(),new DistributedPushSender<TSol>());
        }
        
    },
    
    QueuedIthBroadcasted {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.QueuedIthBroadcasted);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.QueuedIthBroadcasted,new IthPushDecider<TSol>(memorySize,5),new BroadcastPushSender<TSol>());
        }
        
    },
    
    QueuedIthDistributed {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.QueuedIthDistributed);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.QueuedIthDistributed,new IthPushDecider<TSol>(memorySize,5),new DistributedPushSender<TSol>());
        }
        
    },
    
    StateAlwaysBroadcasted {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.StateAlwaysBroadcasted);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.StateAlwaysBroadcasted,new AlwaysPushDecider<TSol>(),new BroadcastPushSender<TSol>());
        }
        
    },
    
    StateAlwaysDistributed {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.StateAlwaysDistributed);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.StateAlwaysDistributed,new AlwaysPushDecider<TSol>(),new DistributedPushSender<TSol>());
        }
        
    },
    
    StateProbableBroadcasted {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.StateProbableBroadcasted);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.StateProbableBroadcasted,new ProbablePushDecider<TSol>(),new BroadcastPushSender<TSol>());
        }
        
    },
    
    StateProbableDistributed {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.StateProbableDistributed);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.StateProbableDistributed,new ProbablePushDecider<TSol>(),new DistributedPushSender<TSol>());
        }
        
    },
    
    StateIthBroadcasted {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.StateIthBroadcasted);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.StateIthBroadcasted,new IthPushDecider<TSol>(memorySize,5),new BroadcastPushSender<TSol>());
        }
        
    },
    
    StateIthDistributed {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.StateIthDistributed);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return new LocalMemorySlots<TSol>(memorySize,MemoryExchangePolicy.StateIthDistributed,new IthPushDecider<TSol>(memorySize,5),new DistributedPushSender<TSol>());
        }
        
    };
    
    abstract<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize);
    abstract<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize);
    
}
