package parallelhyflex;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import mpi.MPI;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class LocalMemorySlots<TSolution extends Solution<TSolution>> extends MemorySlots<TSolution> {
    
    private final Object[] storage;
    private final CompactBitArray notExchangeMask;
    
    public LocalMemorySlots (int memorySize, MemoryExchangePolicy policy) {
        super(policy);
        this.storage = new Object[memorySize];
        this.notExchangeMask = new CompactBitArray(memorySize);
    }
    
    public boolean isLocal () {
        return true;
    }

    public void pushSolution(int index) {
        Communication.Log("pushing " + index);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        Object[] data = new Object[3];
        data[0] = Communication.getCommunication().getRank();
        data[1] = index;
        data[2] = this.storage[index];
        for(int other : Communication.others()) {
            Communication.S(data, 0, 3, MPI.OBJECT, other, 0);
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
    public CompactBitArray getNotExchangeMask() {
        return notExchangeMask;
    }

    public boolean willExchange(int index) {
        return !this.notExchangeMask.get(index);
    }

    @Override
    void receiveSolution(int index, TSolution sol) {
        throw new IllegalArgumentException("Cannot receive on local memory.");
    }
    
}
