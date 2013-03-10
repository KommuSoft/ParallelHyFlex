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
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    Immediately {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Immediately);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    Probable {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Probable);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    Periodically {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Periodically);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    Ith {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Ith);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    DistributedQueued {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new QueueProxyMemorySlots<>(memorySize,MemoryExchangePolicy.DistributedQueued);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    DistributedIth {
        
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.DistributedIth);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    },
    Explicit {
        public<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize) {
            return new StateProxyMemorySlots<>(memorySize,MemoryExchangePolicy.Explicit);
        }
        public<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize) {
            return null;
        }
        
    };
    
    abstract<TSol extends Solution<TSol>> ProxyMemorySlots<TSol> generateReceiver (int memorySize);
    abstract<TSol extends Solution<TSol>> LocalMemorySlots<TSol> generateSender (int memorySize);
    
}
