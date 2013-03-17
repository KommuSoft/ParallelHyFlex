package parallelhyflex.problems.threesat;

import parallelhyflex.ProblemPointerBase;
import parallelhyflex.problemdependent.EnforceableConstraint;

/**
 *
 * @author kommusoft
 */
public class ThreeSatEnforceableConstraint2 extends ProblemPointerBase<ThreeSatSolution,ThreeSatProblem> implements EnforceableConstraint<ThreeSatSolution> {
    
    public ThreeSatEnforceableConstraint2 (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void enforceTrue(ThreeSatSolution solution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isNotSatisfied(ThreeSatSolution solution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
