package parallelhyflex.problemdependent.heuristics;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class CrossoverHeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends HeuristicBase<TSolution,TProblem> {
    
    public CrossoverHeuristicBase(TProblem problem) {
        super(problem,HeuristicType.Crossover);
    }
    
}
