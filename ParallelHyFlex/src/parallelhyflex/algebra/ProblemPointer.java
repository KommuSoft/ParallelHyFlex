package parallelhyflex.algebra;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface ProblemPointer<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> {
    
    public TProblem getProblem ();
    
}
