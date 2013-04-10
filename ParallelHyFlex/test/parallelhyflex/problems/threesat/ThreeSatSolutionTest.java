/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parallelhyflex.TestParameters;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolutionTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

        public ThreeSatSolutionTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSerializationDeserialization() throws IOException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(100, 420);
            ThreeSatProblem tsp = tspg.generateProblem();
            ThreeSatSolutionGenerator tsg = tsp.getSolutionGenerator();
            ThreeSatSolution tss = tsg.generateSolution();
            ByteArrayInputStream bais;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                DataOutputStream dos = new DataOutputStream(baos);
                tss.write(dos);
                dos.close();
                bais = new ByteArrayInputStream(baos.toByteArray());
            }
            DataInputStream dis = new DataInputStream(bais);
            ThreeSatSolution tss2 = tsg.readAndGenerate(dis);
            Assert.assertEquals(tss, tss2);
            dis.close();
            bais.close();
        }
    }
}
