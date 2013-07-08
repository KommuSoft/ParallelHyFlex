package parallelhyflex.algebra;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface SolutionPointer<TSolution extends Solution<TSolution>> {
    
    public TSolution getSolution();
    
}
