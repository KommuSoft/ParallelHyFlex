package parallelhyflex.problemdependent.distance;

import parallelhyflex.ProblemPointerBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class DistanceFunctionBase<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements DistanceFunction<TSolution> {
    
    public DistanceFunctionBase (TProblem problem) {
        super(problem);
    }
    
}
