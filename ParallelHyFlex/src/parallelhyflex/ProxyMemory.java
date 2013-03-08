package parallelhyflex;

import java.util.Arrays;
import mpi.MPI;

/**
 *
 * @author kommusoft
 */
public class ProxyMemory<TSolution extends Solution> {

    private Object[] innerMemory;
    private final Object[][] othersLocalCache;
    private final int[] others;

    public ProxyMemory(int initialMemory) {
        int s = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        this.setInnerMemorySize(initialMemory);
        int[] out = new int[1];
        out[0] = initialMemory;
        this.others = new int[s];
        this.othersLocalCache = new Object[s-1][];
        Communication.AG(out,0, 1, MPI.INT, this.others, 0, 1, MPI.INT);
        for(int i = 0; i < r; i++) {
            othersLocalCache[i] = new Object[this.others[i]];
        }
        for(int i = r; i < s-1; i++) {
            othersLocalCache[i] = new Object[this.others[i+1]];
        }
        System.out.println(""+r+" in says "+Arrays.toString(this.others)+" with "+Arrays.deepToString(this.othersLocalCache));
    }

    public void setInnerMemorySize(int innerMemorySize) {
        Object[] innerMemory = new Object[innerMemorySize];
        if (this.innerMemory != null) {
            int n = Math.min(innerMemorySize, this.innerMemory.length);
            for (int i = 0; i < n; i++) {
                innerMemory[i] = this.innerMemory[i];
            }
        }
        this.innerMemory = innerMemory;

    }

    protected TSolution getSolution(int index) {
        return (TSolution) this.innerMemory[index];
    }

    protected void setSolution(int index, TSolution value) {
        this.innerMemory[index] = value;
    }

    protected void applyHeuristic(Heuristic<TSolution> heuristic, int from, int to) {
        this.setSolution(to, heuristic.applyHeuristic(this.getSolution(from)));
    }

    protected void applyHeuristic(Heuristic<TSolution> heuristic, int from1, int from2, int to) {
        this.setSolution(to, heuristic.applyHeuristic(this.getSolution(from1), this.getSolution(from2)));
    }
}
