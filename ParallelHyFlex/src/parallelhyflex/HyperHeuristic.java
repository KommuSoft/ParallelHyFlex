package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public class HyperHeuristic<TSolution extends Solution<TSolution>> {
    
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
    public double getObjectiveFunction (int objective, int index) {
        return this.problem.getObjectiveFunction(objective).evaluateSolution(this.proxyMemory.getSolution(index));
    }
    public double getDistanceFunction (int distance, int index1, int index2) {
        return this.problem.getDistanceFunction(distance).evaluateDistance(this.proxyMemory.getSolution(index1),this.proxyMemory.getSolution(index2));
    }
    
}
