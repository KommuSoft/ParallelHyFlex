package parallelhyflex.algebra;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @param <TSolution> 
 * @param <TProblem> 
 * @author kommusoft
 */
public interface ProblemSolutionPointer<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends SolutionPointer<TSolution>, ProblemPointer<TSolution,TProblem> {
    
}
