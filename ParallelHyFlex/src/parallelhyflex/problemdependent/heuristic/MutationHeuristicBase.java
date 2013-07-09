package parallelhyflex.problemdependent.heuristic;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class MutationHeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends HeuristicBase<TSolution,TProblem> {
    
    /**
     *
     * @param problem
     */
    public MutationHeuristicBase(TProblem problem) {
        super(problem,HeuristicType.Mutation);
    }
    
}
