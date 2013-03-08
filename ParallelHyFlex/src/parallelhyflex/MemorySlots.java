/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public class MemorySlots<TSolution extends Solution<TSolution>> {
    
    private final boolean local;
    private final MemoryExchangePolicy policy;
    private final Object[] storage;
    
    public MemorySlots (boolean isLocal, int memorySize, MemoryExchangePolicy policy) {
        this.local = isLocal;
        this.policy = policy;
        this.storage = new Object[memorySize];
        //TODO: memorySize
    }

    /**
     * @return the isLocal
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * @return the policy
     */
    public MemoryExchangePolicy getPolicy() {
        return policy;
    }

    public void pushSolution(int index) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void setSolution (int index, TSolution sol) {
        this.storage[index] = sol;
        this.pushSolution(index);
    }
    public TSolution getSolution (int index) {
        return (TSolution) this.storage[index];
        //TODO: update queues
    }
    
    @Override
    public String toString () {
        return String.format("MemorySlots[%s,%s,%s]",this.isLocal(),this.getPolicy(),this.storage.length);
    }
    
}
