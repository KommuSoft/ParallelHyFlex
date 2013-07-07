/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.algebra;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import parallelhyflex.TestParameters;
import parallelhyflex.communication.serialisation.ReadWriteableBaseTest;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class DoubleUpperMatrixTest extends ReadWriteableBaseTest<DoubleUpperMatrix> {

    @Test
    public void testGetSet() {
        System.out.println("get");
        int i = 0;
        int j = 0;
        DoubleUpperMatrix instance = null;
        Double expResult = null;
        Double result = instance.get(i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Override
    public void testSerialisation() throws IOException {
        super.testSerialisation();
    }

    @Test
    public void testSet() {
        System.out.println("set");
        int i = 0;
        int j = 0;
        Double value = null;
        DoubleUpperMatrix instance = null;
        instance.set(i, j, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Override
    public DoubleUpperMatrix createRandomInstance() {
        int n = Utils.nextInt(TestParameters.LOOP2_PARAMETER);
        int N = (n * n - n) / 2;
        double[] data = new double[N];
        for (int i = 0x00; i < N; i++) {
            data[i] = Utils.nextGaussian();
        }
        return new DoubleUpperMatrix(n, data);
    }
}