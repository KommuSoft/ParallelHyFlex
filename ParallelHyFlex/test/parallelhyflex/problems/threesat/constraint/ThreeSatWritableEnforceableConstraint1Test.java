package parallelhyflex.problems.threesat.constraint;

import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraintGenerator;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import junit.framework.Assert;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import java.io.DataOutputStream;
import org.junit.Test;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problems.threesat.ClauseUtils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint1Test extends ThreeSatWritableEnforceableConstraintTestBase {

    /**
     * Test of enforceTrue method, of class
     * ThreeSatWritableEnforceableConstraint1.
     */
    @Test
    @Override
    public void testEnforceTrue1() {
        super.testEnforceTrue1();
    }

    /**
     * Test of enforceTrue method, of class
     * ThreeSatWritableEnforceableConstraint1.
     */
    /**
     * Test of enforceFalse method, of class
     * ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    @Override
    public void testEnforceTrue2() {
        super.testEnforceTrue2();
    }

    /**
     * Test of enforceFalse method, of class
     * ThreeSatWritableEnforceableConstraint1.
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
     */
    @Test
    public void testSerializeDeserialize() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10, 42);
            ThreeSatProblem tsp = tspg.generateProblem();
            ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
            ThreeSatSolutionGenerator tsg = tsp.getSolutionGenerator();
            ThreeSatSolution tss = tsg.generateSolution();
            ThreeSatWritableEnforceableConstraint1 tswec = new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateCompletelyTrueClause(tss.getCompactBitArray()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            tswec.write(dos);
            dos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            baos.close();
            DataInputStream dis = new DataInputStream(bais);
            EnforceableConstraint<ThreeSatSolution> tswec2 = tsweg.readAndGenerate(dis);
            Assert.assertEquals(tswec, tswec2);
            dis.close();
            bais.close();
        }
    }

    @Override
    public WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> renewWritableEnforceableConstraint() {
        return new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateTrueClause(tss.getCompactBitArray()));
    }
}
