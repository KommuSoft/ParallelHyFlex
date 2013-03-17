package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public abstract class EnforceableConstraintBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ConstraintBase<TSolution,TProblem> implements EnforceableConstraint<TSolution> {

    public EnforceableConstraintBase (TProblem problem) {
        super(problem);
    }
    
}
