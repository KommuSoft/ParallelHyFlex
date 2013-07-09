package parallelhyflex.problemdependent.problem;

import parallelhyflex.problemdependent.solution.Solution;


/**
 *
 * @author kommusoft
 * @param <TSolution>
 */
public abstract class ObjectiveFunctionBase<TSolution extends Solution<TSolution>> implements ObjectiveFunction<TSolution> {

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public Double generate(TSolution variable) {
        return this.evaluateSolution(variable);
    }
    
}
