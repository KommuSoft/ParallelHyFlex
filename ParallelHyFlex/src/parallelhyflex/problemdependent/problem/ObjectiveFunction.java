package parallelhyflex.problemdependent.problem;

import parallelhyflex.algebra.Generator;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface ObjectiveFunction<TSolution extends Solution> extends Generator<TSolution,Double> {

    /**
     *
     * @param solution
     * @return
     */
    public double evaluateSolution(TSolution solution);
}
