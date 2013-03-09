package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public class StateProxyMemorySlots<TSolution extends Solution<TSolution>> extends ProxyMemorySlots<TSolution> {
    
    private final Object[] memory;
    
    public StateProxyMemorySlots (int memorySize, MemoryExchangePolicy policy) {
        super(policy);
        this.memory = new Object[memorySize];
    }

    @Override
    public TSolution getSolution(int index) {
        return (TSolution) this.memory[index];
    }

    @Override
    void receiveSolution(int index, TSolution sol) {
        this.memory[index] = sol;
    }

    @Override
    public int getSize() {
        return this.memory.length;
    }
    
}
