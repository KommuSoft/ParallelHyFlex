package parallelhyflex;

import java.util.Arrays;
import mpi.MPI;

/**
 *
 * @author kommusoft
 */
public class ProxyMemory<TSolution extends Solution> {

    private Object[] innerMemory;
    private final int[] others;

    public ProxyMemory(int initialMemory) {
        int s = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        this.setInnerMemorySize(initialMemory);
        int[][] out = new int[s][1];
        for(int i = 0; i < s; i++) {
            out[i][0] = initialMemory;
        }
        int[][] in = new int[s][];
        Communication.getCommunication().AllToAll(out,0, 1, MPI.OBJECT, in, 0, 1, MPI.OBJECT);
        this.others = new int[s];
        for(int i = 0; i < s; i++) {
            this.others[i] = in[i][0];
        }
        System.out.println(""+r+" in says "+Arrays.toString(this.others));
        /*int MAXLEN = 1;
        int tasks = s;
        int out[][] = new int[MAXLEN * tasks][MAXLEN];
        int in[][] = new int[MAXLEN * tasks][];

        for (int k = 0; k < MAXLEN; k++) {
            for (int i = 0; i < MAXLEN * tasks; i++) {
                out[i][k] = (r+1)*k+i;
            }
        }
        
        System.out.println(""+r+" out says "+Arrays.deepToString(out));
        System.out.println(""+r+" in says "+Arrays.deepToString(in));

        MPI.COMM_WORLD.Alltoall(out, 0, MAXLEN, MPI.OBJECT, in, 0, MAXLEN, MPI.OBJECT);
        
        System.out.println(""+r+" out after says "+Arrays.deepToString(out));
        System.out.println(""+r+" in after says "+Arrays.deepToString(in));*/
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
