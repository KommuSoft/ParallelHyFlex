package parallelhyflex.memory;

import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public abstract class MemorySlots<TSolution extends Solution<TSolution>> {

    private final MemoryExchangePolicy policy;

    /**
     *
     * @param policy
     */
    public MemorySlots(MemoryExchangePolicy policy) {
        this.policy = policy;
    }

    /**
     * @return the isLocal
     */
    public abstract boolean isLocal();

    /**
     * @return the policy
     */
    public MemoryExchangePolicy getPolicy() {
        return policy;
    }

    /**
     *
     * @param index
     */
    public abstract void pushSolution(int index);

    /**
     *
     * @param index
     * @param sol
     */
    public abstract void setSolution(int index, TSolution sol);

    /**
     *
     * @param index
     * @return
     */
    public abstract TSolution getSolution(int index);

    /**
     *
     * @param index
     * @return
     */
    public TSolution peekSolution(int index) {
        return this.getSolution(index);
    }

    abstract void receiveSolution(int index, TSolution sol);

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("MemorySlots[%s,%s,%s]", this.isLocal(), this.getPolicy(), this.getSize());
    }

    /**
     *
     * @return
     */
    public abstract int getSize();

    /**
     * @return the notExchangeMask
     */
    public abstract CompactBitArray getBlockingMask();

    /**
     *
     * @param index
     * @return
     */
    public abstract boolean willExchange(int index);
}
