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
    public void testEnforceTrue1() {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10,42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint2 tswec = new ThreeSatWritableEnforceableConstraint2(tsp,tss.clone(),5);
        tswec.enforceTrue(tss);
        Assert.assertTrue(tswec.isSatisfied(tss));
    }
    
    /**
     * Test of enforceTrue method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testEnforceTrue2() {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10,42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint2 tswec = new ThreeSatWritableEnforceableConstraint2(tsp,tss.clone(),5);
        for(int i = 0; i < 20000000; i++) {
            ThreeSatSolution tss2 = tsg.generateSolution();
            tswec.enforceTrue(tss2);
            Assert.assertTrue(tswec.isSatisfied(tss2));
        }
    }

    /**
     * Test of enforceFalse method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testEnforceFalse1() {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10,42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint2 tswec = new ThreeSatWritableEnforceableConstraint2(tsp,tss.clone(),5);
        tswec.enforceFalse(tss);
        Assert.assertFalse(tswec.isSatisfied(tss));
    }
    
    /**
     * Test of enforceFalse method, of class ThreeSatWritableEnforceableConstraint2.
     */
    @Test
    public void testEnforceFalse2() {
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(10,42);
        ThreeSatProblem tsp = tspg.generateProblem();
        ThreeSatWritableEnforceableConstraintGenerator tsweg = new ThreeSatWritableEnforceableConstraintGenerator(tsp);
        ThreeSatSolutionGenerator tsg = tsp.getGenerator();
        ThreeSatSolution tss = tsg.generateSolution();
        ThreeSatWritableEnforceableConstraint2 tswec = new ThreeSatWritableEnforceableConstraint2(tsp,tss.clone(),5);
        for(int i = 0; i < 20000000; i++) {
            ThreeSatSolution tss2 = tsg.generateSolution();
            tswec.enforceFalse(tss2);
            Assert.assertFalse(tswec.isSatisfied(tss2));
        }
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
