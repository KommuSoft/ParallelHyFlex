package parallelhyflex;

import parallelhyflex.problemdependent.Problem;
import parallelhyflex.problemdependent.Solution;

/**
 *
 * @author kommusoft
 */
public interface ProblemPointer<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> {
    
    public TProblem getProblem ();
    
}
