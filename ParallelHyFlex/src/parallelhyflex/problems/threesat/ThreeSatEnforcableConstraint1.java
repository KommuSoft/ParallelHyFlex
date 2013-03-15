package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.EnforcableConstraint;

/**
 *
 * @author kommusoft
 */
public class ThreeSatEnforcableConstraint1 implements EnforcableConstraint<ThreeSatSolution> {

    private long data;
    
    /*public ThreeSatEnforcableConstraint1 (Problem prob, ) {
        
    }*/

    @Override
    public void enforceTrue(ThreeSatSolution solution) {
        
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        return solution.satisfiesClause(data);
    }

    @Override
    public boolean isNotSatisfied(ThreeSatSolution solution) {
        return !solution.satisfiesClause(data);
    }
}
