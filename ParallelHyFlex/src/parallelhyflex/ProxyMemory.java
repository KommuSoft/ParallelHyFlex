package parallelhyflex;

import java.util.Arrays;
import mpi.MPI;

/**
 *
 * @author kommusoft
 */
public class ProxyMemory<TSolution extends Solution<TSolution>> {

    private Object[] innerMemory;
    private final Object[][] othersLocalCache;
    private final int[] others;
    private int totalMemory = 0;

    public ProxyMemory(int initialMemory) {
        int s = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        this.setInnerMemorySize(initialMemory);
        int[] out = new int[1];
        out[0] = initialMemory;
        this.others = new int[s];
        this.othersLocalCache = new Object[s-1][];
        Communication.AG(out,0, 1, MPI.INT, this.others, 0, 1, MPI.INT);
        int sum = 0, ni;
        for(int i = 0; i < r; i++) {
            ni = this.others[i];
            sum += ni;
            othersLocalCache[i] = new Object[ni];
        }
        sum += this.others[r];
        for(int i = r; i < s-1; i++) {
            ni = this.others[i+1];
            sum += ni;
            othersLocalCache[i] = new Object[ni];
        }
        this.totalMemory = sum;
        //System.out.println(""+r+" in says "+Arrays.toString(this.others)+" with "+Arrays.deepToString(this.othersLocalCache));
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
    public int getMemorySize () {
        return this.totalMemory;
    }

    protected TSolution getSolution(int index) {
        return (TSolution) this.innerMemory[index];
    }

    protected void setSolution(int index, TSolution value) {
        this.innerMemory[index] = value;
        pushSolution(index);
    }

    //TODO: carefull with heuristics applied to global solution space
    protected void applyHeuristic(Heuristic<TSolution> heuristic, int from, int to) {
        if(from == to) {
            heuristic.applyHeuristicLocally(this.getSolution(from));
            pushSolution(to);
        }
        else {
            this.setSolution(to, heuristic.applyHeuristic(this.getSolution(from)));
        }
    }

    protected void applyHeuristic(Heuristic<TSolution> heuristic, int from1, int from2, int to) {
        if(from1 == to) {
            heuristic.applyHeuristicLocally(this.getSolution(from1),this.getSolution(from2));
            pushSolution(to);
        }
        else {
            this.setSolution(to, heuristic.applyHeuristic(this.getSolution(from1), this.getSolution(from2)));
        }
    }

    private void pushSolution(int to) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
