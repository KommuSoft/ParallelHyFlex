package parallelhyflex.problemdependent.heuristic;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @param <TSolution> 
 * @param <TProblem> 
 * @author kommusoft
 */
public abstract class CrossoverHeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends HeuristicBase<TSolution,TProblem> {
    
    /**
     *
     * @param problem
     */
    public CrossoverHeuristicBase(TProblem problem) {
        super(problem,HeuristicType.Crossover);
    }
    
    /**
     * Do nothing, this is a crossover heuristic
     * @param from 
     */
    @Override
    public final void applyHeuristicLocally(TSolution from) {
    }
    
    /**
     *
     * @param from1
     * @param from2
     */
    @Override
    public abstract void applyHeuristicLocally (TSolution from1, TSolution from2);
    
}
