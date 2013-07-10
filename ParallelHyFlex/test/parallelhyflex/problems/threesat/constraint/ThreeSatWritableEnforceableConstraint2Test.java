package parallelhyflex.problems.threesat.constraint;

import org.junit.Test;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGenerator;
import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraintBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.constraint.WriteableEnforceableConstraintTest;
import parallelhyflex.problems.threesat.ThreeSatRenewalStrategy;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWriteableEnforceableConstraint2Test extends WriteableEnforceableConstraintTest<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> {

    /**
     *
     */
    public ThreeSatWriteableEnforceableConstraint2Test() {
    }

    /**
     * Test of enforceTrue method, of class
     * ThreeSatWriteableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceTrue1() {
        super.testEnforceTrue1();
    }

    /**
     * Test of enforceTrue method, of class
     * ThreeSatWriteableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceTrue2() {
        super.testEnforceTrue2();
    }

    /**
     * Test of enforceFalse method, of class
     * ThreeSatWriteableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceFalse1() {
        super.testEnforceFalse1();
    }

    /**
     * Test of enforceFalse method, of class
     * ThreeSatWriteableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceFalse2() {
        super.testEnforceFalse2();
    }

    /**
     * Test of serialisation/deserialisation method, of class
     * ThreeSatWriteableEnforceableConstraint1.
     *
     * @throws Exception
     */
    @Test
    @Override
    public void testSerializeDeserialize() throws Exception {
        super.testSerializeDeserialize();
    }

    /**
     *
     * @return
     */
    @Override
    public WriteableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> renewWriteableEnforceableConstraint() {
        return new ThreeSatWriteableEnforceableConstraint2(getTsp(), getTss().clone(), 5);
    }

    /**
     *
     * @return
     */
    @Override
    public EnforceableConstraintGenerator<ThreeSatSolution, ? extends EnforceableConstraint<ThreeSatSolution>> renewEnforceableConstraintGenerator() {
        return new ThreeSatWriteableEnforceableConstraintGenerator(this.getTsp());
    }

    /**
     *
     * @return
     */
    @Override
    public TestRenewalStrategy<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> getRenewalStrategy() {
        return new ThreeSatRenewalStrategy();
    }
}
