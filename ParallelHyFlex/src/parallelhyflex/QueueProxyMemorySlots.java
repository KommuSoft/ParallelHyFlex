package parallelhyflex;

import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 *
 * @author kommusoft
 */
public class QueueProxyMemorySlots<TSolution extends Solution<TSolution>> extends ProxyMemorySlots<TSolution> {
    
    protected final LinkedList<TSolution>[] solutionQueue;
    
    public QueueProxyMemorySlots (int memorySize, MemoryExchangePolicy policy) {
        super(policy);
        LinkedList<TSolution> dummy = new LinkedList<>();
        this.solutionQueue = (LinkedList<TSolution>[]) Array.newInstance(dummy.getClass(), memorySize);
        if(memorySize > 0) {
            this.solutionQueue[0] = dummy;
            for(int i = 1; i < memorySize; i++) {
                this.solutionQueue[i] = new LinkedList<>();
            }
        }
    }

    @Override
    public TSolution getSolution(int index) {
        return this.solutionQueue[index].pop();
    }

    @Override
    public int getSize() {
        return this.solutionQueue.length;
    }

    @Override
    void receiveSolution(int index, TSolution sol) {
        this.solutionQueue[index].add(sol);
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
