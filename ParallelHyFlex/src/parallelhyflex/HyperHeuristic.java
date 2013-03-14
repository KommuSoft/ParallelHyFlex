package parallelhyflex;

import parallelhyflex.problemdependent.Solution;
import parallelhyflex.problemdependent.Problem;

/**
 *
 * @author kommusoft
 */
public class HyperHeuristic<TSolution extends Solution<TSolution>> {
    
    private final ProxyMemory<TSolution> proxyMemory;
    private final Problem<TSolution> problem;
    
    public HyperHeuristic (Problem<TSolution> problem) {
        this.problem = problem;
        this.proxyMemory = new ProxyMemory<TSolution>((int) Math.floor(Math.random()*10),MemoryExchangePolicy.values()[Communication.getCommunication().getRank()]);
        int nw = this.getWritableMemory();
        for(int i = 0; i < nw; i++) {
            this.initializeSolution(i);
        }
    }
    public void applyHeuristic (int heuristic, int from, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from, to);
    }
    public void applyHeuristic (int heuristic, int from1, int from2, int to) {
        this.proxyMemory.applyHeuristic(problem.getHeuristic(heuristic), from1, from2, to);
    }
    public void initializeSolution (int index) {
        this.proxyMemory.setSolution(index,this.problem.getSolutionGenerator().generateSolution());
    }
    public double getObjectiveFunction (int objective, int index) {
        return this.problem.getObjectiveFunction(objective).evaluateSolution(this.proxyMemory.getSolution(index));
    }
    public int getReadableMemory () {
        return this.proxyMemory.getMemorySize();
    }
    public int getWritableMemory () {
        return this.proxyMemory.getLocalMemorySize();
    }
    public double getDistanceFunction (int distance, int index1, int index2) {
        return this.problem.getDistanceFunction(distance).evaluateDistance(this.proxyMemory.getSolution(index1),this.proxyMemory.getSolution(index2));
    }
    public boolean areEqual (int solution1, int solution2) {
        return this.proxyMemory.peekSolution(solution1).equalSolution(this.proxyMemory.peekSolution(solution2));
    }
    
}
