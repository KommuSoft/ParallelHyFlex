package parallelhyflex.problemdependent.problem;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface ObjectiveFunction<TSolution extends Solution> {
    
    public double evaluateSolution (TSolution solution);
    
}
