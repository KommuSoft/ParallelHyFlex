package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public enum MemoryExchangePolicy {
    
    Queued {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Queued);
        }
    },
    Immediately {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Immediately);
        }
    },
    Probable {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Probable);
        }
    },
    Periodically {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Periodically);
        }
    },
    Ith {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Ith);
        }
    },
    DistributedQueued {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.DistributedQueued);
        }
    };
    
    abstract<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize);
    
}
