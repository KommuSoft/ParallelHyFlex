package parallelhyflex.problemdependent.problem;

import parallelhyflex.problemdependent.solution.Solution;


public abstract class ObjectiveFunctionBase<TSolution extends Solution<TSolution>> implements ObjectiveFunction<TSolution> {

    @Override
    public Double generate(TSolution variable) {
        return this.evaluateSolution(variable);
    }
    
}
