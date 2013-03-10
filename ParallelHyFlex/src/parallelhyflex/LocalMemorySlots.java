package parallelhyflex;

import parallelhyflex.pushsenders.PushSender;
import parallelhyflex.pushdeciders.PushDecider;
import mpi.MPI;
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
    
    public LocalMemorySlots (int memorySize, MemoryExchangePolicy policy, PushDecider<TSolution> pushDecider, PushSender<TSolution> pushSender) {
        super(policy);
        this.storage = new Object[memorySize];
        this.blockingMask = new CompactBitArray(memorySize);
        this.pushDecider = pushDecider;
        this.pushSender = pushSender;
    }
    
    public boolean isLocal () {
        return true;
    }

    public void pushSolution(int index) {
        if(this.willExchange(index)) {
        }
    }
    
    //protected abstract void sendSolution ();

    public void setSolution(int index, TSolution sol) {
        this.storage[index] = sol;
        this.pushSolution(index);
    }

    public TSolution getSolution(int index) {
        return (TSolution) this.storage[index];
    }

    public int getSize() {
        return this.storage.length;
    }

    /**
     * @return the notExchangeMask
     */
    public CompactBitArray getBlockingMask() {
        return blockingMask;
    }

    public boolean willExchange(int index) {
        return !this.blockingMask.get(index);
    }

    @Override
    void receiveSolution(int index, TSolution sol) {
        throw new IllegalArgumentException("Cannot receive on local memory.");
    }
    
}
