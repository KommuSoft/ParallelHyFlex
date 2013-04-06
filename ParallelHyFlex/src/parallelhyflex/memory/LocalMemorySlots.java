package parallelhyflex.memory;

import parallelhyflex.memory.deciders.PushDecider;
import parallelhyflex.memory.senders.PushSender;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class LocalMemorySlots<TSolution extends Solution<TSolution>> extends MemorySlots<TSolution> {

    private final Object[] storage;
    private final CompactBitArray blockingMask;
    private final PushDecider<TSolution> pushDecider;
    private final PushSender<TSolution> pushSender;

    public LocalMemorySlots(int memorySize, MemoryExchangePolicy policy, PushDecider<TSolution> pushDecider, PushSender<TSolution> pushSender) {
        super(policy);
        this.storage = new Object[memorySize];
        this.blockingMask = new CompactBitArray(memorySize);
        this.pushDecider = pushDecider;
        this.pushSender = pushSender;
    }

    @Override
    public boolean isLocal() {
        return true;
    }

    @Override
    public void pushSolution(int index) {
        if (this.willExchange(index)) {
            TSolution sol = (TSolution) storage[index];
            if (this.pushDecider.decidePush(index, sol)) {
                this.pushSender.sendSolution(index, sol);
            }
        }
    }

    //protected abstract void sendSolution ();
    @Override
    public void setSolution(int index, TSolution sol) {
        if (index < this.storage.length) {
            this.storage[index] = sol;
            this.pushSolution(index);
        } else {
            throw new RuntimeException("One can only write to local memory!");
        }
    }

    @Override
    public TSolution getSolution(int index) {
        return (TSolution) this.storage[index];
    }

    @Override
    public int getSize() {
        return this.storage.length;
    }

    /**
     * @return the notExchangeMask
     */
    @Override
    public CompactBitArray getBlockingMask() {
        return blockingMask;
    }

    @Override
    public boolean willExchange(int index) {
        return !this.blockingMask.get(index);
    }

    @Override
    void receiveSolution(int index, TSolution sol) {
        throw new IllegalArgumentException("Cannot receive on local memory.");
    }
}
