package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public abstract class WritableEnforceableConstraintBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends EnforceableConstraintBase<TSolution,TProblem> implements WritableEnforceableConstraint<TSolution> {

    public WritableEnforceableConstraintBase (TProblem problem) {
        super(problem);
    }
    
}