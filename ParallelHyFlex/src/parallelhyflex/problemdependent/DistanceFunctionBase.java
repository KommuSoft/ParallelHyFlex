package parallelhyflex.problemdependent;

import parallelhyflex.ProblemPointerBase;

/**
 *
 * @author kommusoft
 */
public abstract class DistanceFunctionBase<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements DistanceFunction<TSolution> {
    
    public DistanceFunctionBase (TProblem problem) {
        super(problem);
    }
    
}
