package parallelhyflex.problemdependent.distance;

import parallelhyflex.algebra.ProblemPointerBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class DistanceFunctionBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements DistanceFunction<TSolution> {

    /**
     *
     * @param problem
     */
    public DistanceFunctionBase(TProblem problem) {
        super(problem);
    }
}
