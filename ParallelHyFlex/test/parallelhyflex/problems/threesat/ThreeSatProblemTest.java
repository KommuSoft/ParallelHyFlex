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

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemTest {
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

        public ThreeSatProblemTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSerializationDeserialization() throws IOException {
        for(int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(100,420);
            ThreeSatProblem tsp = tspg.generateProblem();
            ByteArrayInputStream bais;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                DataOutputStream dos = new DataOutputStream(baos);
                tsp.write(dos);
                dos.close();
                bais = new ByteArrayInputStream(baos.toByteArray());
            }
            DataInputStream dis = new DataInputStream(bais);
            ThreeSatProblem tsp2 = tspg.readAndGenerate(dis);
            Assert.assertEquals(tsp, tsp2);
            dis.close();
            bais.close();
        }
    }
}
