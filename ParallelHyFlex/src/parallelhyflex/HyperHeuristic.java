package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public class HyperHeuristic<TSolution extends Solution> {
    
    private final ProxyMemory<TSolution> proxyMemory;
    private final Problem<TSolution> problem;
    
    public HyperHeuristic (Problem<TSolution> problem) {
        this.problem = problem;
        this.proxyMemory = new ProxyMemory<TSolution>(Communication.getCommunication().getRank()+1);
    }
    
    public void setInnerMemory (int size) {
        this.proxyMemory.setInnerMemorySize(size);
    }
    public void applyHeuristic (int heuristic, int from, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from, to);
    }
    public void applyHeuristic (int heuristic, int from1, int from2, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from1, from2, to);
    }
    
}
