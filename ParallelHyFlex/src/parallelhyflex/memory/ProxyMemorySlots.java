package parallelhyflex.memory;

import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public abstract class ProxyMemorySlots<TSolution extends Solution<TSolution>> extends MemorySlots<TSolution> {

    public ProxyMemorySlots(MemoryExchangePolicy policy) {
        super(policy);
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public void pushSolution(int index) {
        throw new IllegalArgumentException("Cannot write at this index: region is read-only!");
    }

    @Override
    public void setSolution(int index, TSolution sol) {
        throw new IllegalArgumentException("Connot write at this index: region is read-only!");
    }

    @Override
    public CompactBitArray getBlockingMask() {
        return null;
    }

    @Override
    public boolean willExchange(int index) {
        return false;
    }
}
