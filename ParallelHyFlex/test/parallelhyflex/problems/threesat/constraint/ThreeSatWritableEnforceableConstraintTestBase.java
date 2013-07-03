package parallelhyflex.problems.threesat.constraint;

import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problems.threesat.ThreeSatRenewalStrategy;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatWritableEnforceableConstraintTestBase extends ThreeSatRenewalStrategy {

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
            tswec.enforceTrue(getTss());
            Assert.assertTrue(tswec.isSatisfied(getTss()));
            Assert.assertFalse(tswec.isNotSatisfied(getTss()));
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
            Assert.assertTrue(String.format("failed with 3SWEC=%s", tswec), tswec.isSatisfied(tss2));
            Assert.assertFalse(String.format("failed with 3SWEC=%s", tswec), tswec.isNotSatisfied(tss2));
        }
    }

    protected void renewSolution2() {
        this.tss2 = getTsg().generateSolution();
    }

    public void testEnforceFalse1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.renewSolutionGenerator();
            this.renewSolution();
            tswec = this.renewWritableEnforceableConstraint();
            tswec.enforceFalse(getTss());
            Assert.assertFalse(tswec.isSatisfied(getTss()));
            Assert.assertTrue(tswec.isNotSatisfied(getTss()));
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
