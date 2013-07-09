package parallelhyflex.problemdependent.heuristic;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class LocalSearchHeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends HeuristicBase<TSolution,TProblem> {
    
    /**
     *
     * @param problem
     */
    public LocalSearchHeuristicBase(TProblem problem) {
        super(problem,HeuristicType.LocalSearch);
    }
    
}
