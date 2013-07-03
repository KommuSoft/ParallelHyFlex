package parallelhyflex.problems.threesat.constraint;

import org.junit.Test;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.constraint.WritableEnforceableConstraintTest;
import parallelhyflex.problems.threesat.ThreeSatRenewalStrategy;
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
public class ThreeSatWritableEnforceableConstraint2Test extends WritableEnforceableConstraintTest<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> {

    public ThreeSatWritableEnforceableConstraint2Test() {
    }

    /**
     * Test of enforceTrue method, of class
     * ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceTrue1() {
        super.testEnforceTrue1();
    }

    /**
     * Test of enforceTrue method, of class
     * ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceTrue2() {
        super.testEnforceTrue2();
    }

    /**
     * Test of enforceFalse method, of class
     * ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceFalse1() {
        super.testEnforceFalse1();
    }

    /**
     * Test of enforceFalse method, of class
     * ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceFalse2() {
        super.testEnforceFalse2();
    }

    /**
     * Test of serialisation/deserialisation method, of class
     * ThreeSatWritableEnforceableConstraint1.
     *
     * @throws Exception
     */
    @Test
    @Override
    public void testSerializeDeserialize() throws Exception {
        super.testSerializeDeserialize();
    }

    @Override
    public WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> renewWritableEnforceableConstraint() {
        return new ThreeSatWritableEnforceableConstraint2(getTsp(), getTss().clone(), 5);
    }

    @Override
    public EnforceableConstraintGenerator<ThreeSatSolution, ? extends EnforceableConstraint<ThreeSatSolution>> renewEnforceableConstraintGenerator() {
        return new ThreeSatWritableEnforceableConstraintGenerator(this.getTsp());
    }

    @Override
    public TestRenewalStrategy<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> getRenewalStrategy() {
        return new ThreeSatRenewalStrategy();
    }
}
