package parallelhyflex;

import parallelhyflex.communication.Communication;
import parallelhyflex.problemdependent.Solution;
import parallelhyflex.problemdependent.Heuristic;
import java.lang.reflect.Array;
import java.util.Arrays;
import mpi.MPI;
import parallelhyflex.problemdependent.WritableExperience;

/**
 *
 * @author kommusoft
 */
public class ProxyMemory<TSolution extends Solution<TSolution>> {
    
    private final MemorySlots<TSolution>[] solutionCache;
    private final MemorySlots localSlots;
    private final WritableExperience<TSolution,?> writableExperience;
    private final int[][] others;
    private int totalMemory = 0;

    public ProxyMemory(int initialMemory, MemoryExchangePolicy localPolicy, WritableExperience<TSolution,?> writableExperience) {
        this.writableExperience = writableExperience;
        MemoryExchangePolicy[] policies = MemoryExchangePolicy.values();
        int s = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        int[][] out = new int[1][2];
        out[0][0] = initialMemory;
        out[0][1] = localPolicy.ordinal();
        this.others = new int[s][];
        this.localSlots = localPolicy.generateSender(initialMemory);
        this.solutionCache = (MemorySlots<TSolution>[]) Array.newInstance(this.localSlots.getClass().getSuperclass(),s);
        this.solutionCache[0x00] = this.localSlots;
        Communication.AG(out,0, 1, MPI.OBJECT, this.others, 0, 1, MPI.OBJECT);
        int sum = 0, ni;
        int i = 1;
        for(int j = r+1; j < s; i++, j++) {
            ni = this.others[j][0];
            sum += ni;
            solutionCache[i] = policies[this.others[j][1]].generateReceiver(ni);
        }
        sum += this.others[r][0];
        for(int j = 0; j < r; i++, j++) {
            ni = this.others[j][0];
            sum += ni;
            solutionCache[i] = policies[this.others[j][1]].generateReceiver(ni);
        }
        this.totalMemory = sum;
        //Communication.Log(Arrays.deepToString(this.others)+" with "+Arrays.toString(solutionCache));
        new FetchThread().start();
    }
    
    public int getMemorySize () {
        return this.totalMemory;
    }
    
    public int getLocalMemorySize () {
        return this.localSlots.getSize();
    }

    protected TSolution getSolution(int index) {
        //return (TSolution) this.innerMemory[index];
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected void setSolution(int index, TSolution value) {
        this.getWritableExperience().join(value);
        this.localSlots.setSolution(index,value);
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
        this.getWritableExperience().join(this.getSolution(to));
        this.localSlots.pushSolution(to);
    }

    public Solution peekSolution(int index) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    private int rankToIndex (int rank) {
        int myr = Communication.getCommunication().getRank();
        if(rank > myr) {
            return rank-myr;
        }
        else {
            int siz = Communication.getCommunication().getSize();
            return siz-myr+rank;
        }
    }

    /**
     * @return the writableExperience
     */
    public WritableExperience<TSolution,?> getWritableExperience() {
        return writableExperience;
    }
    
    private class FetchThread extends Thread {
        
        public FetchThread () {
            this.setDaemon(true);
        }
        
        public void run () {
            Object[] buffer = new Object[3];
            while(true) {
                Communication.RV(buffer,0,3,MPI.OBJECT,MPI.ANY_SOURCE,0);
                Communication.Log("received "+Arrays.toString(buffer));
                solutionCache[rankToIndex((int) buffer[0])].receiveSolution((int) buffer[1],(TSolution) buffer[2]);
            }
        }
    
    }
    
}
