package parallelhyflex;

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
        
    };
    
    abstract<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize);
    abstract<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize);
    
}
