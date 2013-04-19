package parallelhyflex.problems.threesat.constraints;

import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatWritableEnforceableConstraint extends WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> {
    
    protected ThreeSatWritableEnforceableConstraint(ThreeSatProblem problem) {
        super(problem);
    }
    
}
