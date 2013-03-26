package parallelhyflex.problems.threesat;

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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parallelhyflex.TestParameters;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint1Test {

    public ThreeSatWritableEnforceableConstraint1Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of enforceTrue method, of class ThreeSatWritableEnforceableConstraint1.
     */
    @Test
    public void testEnforceTrue1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10, 42);
            ThreeSatProblem tsp = tspg.generateProblem();
            ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
            ThreeSatSolutionGenerator tsg = tsp.getSolutionGenerator();
            ThreeSatSolution tss = tsg.generateSolution();
            ThreeSatWritableEnforceableConstraint1 tswec = new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateTrueClause(tss.getCompactBitArray()));
            tswec.enforceTrue(tss);
            Assert.assertTrue(tss.getCompactBitArray().satisfiesClause(tswec.getConstraint()));
        }
    }

    /**
     * Test of enforceTrue method, of class ThreeSatWritableEnforceableConstraint1.
     */
    @Test
    public void testEnforceTrue2() {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10, 42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getSolutionGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint1 tswec = new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateTrueClause(tss.getCompactBitArray()));
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatSolution tss2 = tsg.generateSolution();
            tswec.enforceTrue(tss2);
            Assert.assertTrue(tss2.getCompactBitArray().satisfiesClause(tswec.getConstraint()));
        }
    }

    /**
     * Test of enforceFalse method, of class ThreeSatWritableEnforceableConstraint1.
     */
    @Test
    public void testEnforceFalse1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10, 42);
            ThreeSatProblem tsp = tspg.generateProblem();
            ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
            ThreeSatSolutionGenerator tsg = tsp.getSolutionGenerator();
            ThreeSatSolution tss = tsg.generateSolution();
            ThreeSatWritableEnforceableConstraint1 tswec = new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateTrueClause(tss.getCompactBitArray()));
            tswec.enforceFalse(tss);
            Assert.assertFalse(tss.getCompactBitArray().satisfiesClause(tswec.getConstraint()));
        }
    }

    @Test
    public void testEnforceFalse2() {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10, 42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getSolutionGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint1 tswec = new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateTrueClause(tss.getCompactBitArray()));
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatSolution tss2 = tsg.generateSolution();
            tswec.enforceFalse(tss2);
            Assert.assertFalse(tss2.getCompactBitArray().satisfiesClause(tswec.getConstraint()));
        }
    }

    /**
     * Test of serialisation/deserialisation method, of class ThreeSatWritableEnforceableConstraint1.
     */
    @Test
    public void testSerializeDeserialize() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10, 42);
            ThreeSatProblem tsp = tspg.generateProblem();
            ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
            ThreeSatSolutionGenerator tsg = tsp.getGenerator();
            ThreeSatSolution tss = tsg.generateSolution();
            ThreeSatWritableEnforceableConstraint1 tswec = new ThreeSatWritableEnforceableConstraint1(tsp, ClauseUtils.generateTrueClause(tss.getCompactBitArray()));
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
}
