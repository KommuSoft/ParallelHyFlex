package parallelhyflex.problemdependent;

import parallelhyflex.ProblemPointerBase;

/**
 *
 * @author kommusoft
 */
public abstract class ConstraintBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements Constraint<TSolution> {
    
    public ConstraintBase (TProblem problem) {
        super(problem);
    }

    @Override
    public boolean isNotSatisfied(TSolution solution) {
        return !this.isSatisfied(solution);
    }
    
}
