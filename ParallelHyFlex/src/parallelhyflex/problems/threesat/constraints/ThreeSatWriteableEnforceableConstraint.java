package parallelhyflex.problems.threesat.constraints;

import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraintBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatWriteableEnforceableConstraint extends WriteableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> {
    
    protected ThreeSatWriteableEnforceableConstraint(ThreeSatProblem problem) {
        super(problem);
    }
    
}
