package parallelhyflex.problemdependent.heuristic;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class RuinRecreateHeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends HeuristicBase<TSolution,TProblem> {
    
    /**
     *
     * @param problem
     */
    public RuinRecreateHeuristicBase(TProblem problem) {
        super(problem,HeuristicType.RuinRecreate);
    }
    
}
