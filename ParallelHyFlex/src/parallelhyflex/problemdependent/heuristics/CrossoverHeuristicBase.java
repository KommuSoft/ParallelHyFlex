package parallelhyflex.problemdependent.heuristics;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @param <TSolution> 
 * @param <TProblem> 
 * @author kommusoft
 */
public abstract class CrossoverHeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends HeuristicBase<TSolution,TProblem> {
    
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
    
    @Override
    public abstract void applyHeuristicLocally (TSolution from1, TSolution from2);
    
}
