package parallelhyflex.memory;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class StateProxyMemorySlots<TSolution extends Solution<TSolution>> extends ProxyMemorySlots<TSolution> {

    private final Object[] memory;

    /**
     *
     * @param memorySize
     * @param policy
     */
    public StateProxyMemorySlots(int memorySize, MemoryExchangePolicy policy) {
        super(policy);
        this.memory = new Object[memorySize];
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public TSolution getSolution(int index) {
        return (TSolution) this.memory[index];
    }

    @Override
    void receiveSolution(int index, TSolution sol) {
        this.memory[index] = sol;
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.memory.length;
    }
}
