package parallelhyflex;

import java.lang.reflect.Array;

/**
 *
 * @author kommusoft
 */
public class ProxyMemory<TSolution extends Solution> {
    
    private Object[] innerMemory;
    
    public void setInnerMemorySize (int innerMemorySize) {
        Object[] innerMemory = new Object[innerMemorySize];
        if(this.innerMemory != null) {
            int n = Math.min(innerMemorySize, this.innerMemory.length);
            for(int i = 0; i < n; i++) {
                innerMemory[i] = this.innerMemory[i];
            }
        }
        this.innerMemory = innerMemory;
    }
    protected TSolution getSolution (int index) {
        return (TSolution) this.innerMemory[index];
    }
    protected void setSolution (int index, TSolution value) {
        this.innerMemory[index] = value;
    }
    protected void applyHeuristic (Heuristic<TSolution> heuristic, int from, int to) {
        this.setSolution(to,heuristic.applyHeuristic(this.getSolution(from)));
    }
    protected void applyHeuristic (Heuristic<TSolution> heuristic, int from1, int from2, int to) {
        this.setSolution(to,heuristic.applyHeuristic(this.getSolution(from1),this.getSolution(from2)));
    }
    
}
