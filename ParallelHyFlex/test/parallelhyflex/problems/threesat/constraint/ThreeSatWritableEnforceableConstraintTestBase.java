/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.constraint;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problemdependent.heuristics.HeuristicBase;
import parallelhyflex.problems.threesat.ThreeSatTestBase;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint2;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraintGenerator;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatWritableEnforceableConstraintTestBase extends ThreeSatTestBase {
    
    protected WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> tswec;
    
    public abstract WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> renewWritableEnforceableConstraint();
    
    public void testEnforceTrue1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.renewSolutionGenerator();
            this.renewSolution();
            tswec = this.renewWritableEnforceableConstraint();
            tswec.enforceTrue(tss);
            Assert.assertTrue(tswec.isSatisfied(tss));
            Assert.assertFalse(tswec.isNotSatisfied(tss));
        }
    }
    
    public void testEnforceFalse1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.renewSolutionGenerator();
            this.renewSolution();
            tswec = this.renewWritableEnforceableConstraint();
            tswec.enforceFalse(tss);
            Assert.assertFalse(tswec.isSatisfied(tss));
            Assert.assertTrue(tswec.isNotSatisfied(tss));
        }
    }
    
}
