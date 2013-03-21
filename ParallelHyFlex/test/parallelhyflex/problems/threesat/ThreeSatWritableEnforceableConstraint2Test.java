/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.EnforceableConstraint;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import junit.framework.Assert;
import java.io.DataOutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint2Test {
    
    public ThreeSatWritableEnforceableConstraint2Test() {
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
     * Test of enforceTrue method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testEnforceTrue() {
        System.out.println("enforceTrue");
        ThreeSatSolution solution = null;
        ThreeSatWritableEnforceableConstraint2 instance = null;
        instance.enforceTrue(solution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enforceFalse method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testEnforceFalse() {
        System.out.println("enforceFalse");
        ThreeSatSolution solution = null;
        ThreeSatWritableEnforceableConstraint2 instance = null;
        instance.enforceFalse(solution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSatisfied method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testIsSatisfied() {
        System.out.println("isSatisfied");
        ThreeSatSolution solution = null;
        ThreeSatWritableEnforceableConstraint2 instance = null;
        boolean expResult = false;
        boolean result = instance.isSatisfied(solution);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoot method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        ThreeSatWritableEnforceableConstraint2 instance = null;
        ThreeSatSolution expResult = null;
        ThreeSatSolution result = instance.getRoot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxDistance method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testGetMaxDistance() {
        System.out.println("getMaxDistance");
        ThreeSatWritableEnforceableConstraint2 instance = null;
        int expResult = 0;
        int result = instance.getMaxDistance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of serialisation/deserialisation method, of class ThreeSatWritableEnforceableConstraint1.
     */
    @Test
    public void testSerializeDeserialize() throws Exception {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10,42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint2 tswec = new ThreeSatWritableEnforceableConstraint2(tsp,tss,50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        tswec.write(dos);
        dos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        baos.close();
        DataInputStream dis = new DataInputStream(bais);
        EnforceableConstraint<ThreeSatSolution> tswec2 = tsweg.readAndGenerate(dis);
        System.out.println(tswec2.toString());
        Assert.assertEquals(tswec, tswec2);
        dis.close();
        bais.close();
    }
}
