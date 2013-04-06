/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.constraint;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problems.threesat.ThreeSatTestBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatWritableEnforceableConstraintTestBase extends ThreeSatTestBase {

    protected WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> tswec;
    protected ThreeSatSolution tss2;

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

    public void testEnforceTrue2() {
        this.renewProblemGenerator();
        this.renewProblem();
        this.renewSolutionGenerator();
        this.renewSolution();
        tswec = this.renewWritableEnforceableConstraint();
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewSolution2();
            tswec.enforceTrue(tss2);
            Assert.assertTrue(tswec.isSatisfied(tss2));
            Assert.assertFalse(tswec.isNotSatisfied(tss2));
        }
    }

    protected void renewSolution2() {
        this.tss2 = tsg.generateSolution();
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

    public void testEnforceFalse2() {
        this.renewProblemGenerator();
        this.renewProblem();
        this.renewSolutionGenerator();
        this.renewSolution();
        tswec = this.renewWritableEnforceableConstraint();
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewSolution2();
            tswec.enforceFalse(tss2);
            Assert.assertFalse(tswec.isSatisfied(tss2));
            Assert.assertTrue(tswec.isNotSatisfied(tss2));
        }
    }
}
