package parallelhyflex.algebra;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import parallelhyflex.TestParameters;
import parallelhyflex.communication.serialisation.ReadWriteableBaseTest;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class DoubleUpperMatrixTest extends ReadWriteableBaseTest<DoubleUpperMatrix> {

    /**
     *
     */
    @Test
    public void testGetSet() {
        for (int i = 0x00; i < TestParameters.LOOP_PARAMETER; i++) {
            int n = i + 0x10;
            double[][] vals = new double[n][n];
            for (int a = 0x00; a < n; a++) {
                for (int b = 0x00; b < n; b++) {
                    vals[a][b] = 0.0d;
                }
                vals[a][a] = Double.NaN;
            }
            DoubleUpperMatrix dum = new DoubleUpperMatrix(n);
            for (int j = 0x00; j < TestParameters.LOOP_PARAMETER; j++) {
                double val = Utils.nextGaussian();
                int i0 = Utils.nextInt(n), j0 = Utils.ignoreRandomIndex(n, i0);
                vals[i0][j0] = val;
                vals[j0][i0] = val;
                dum.set(i0, j0, val);
                for (int a = 0x00; a < n; a++) {
                    for (int b = 0x00; b < n; b++) {
                        Assert.assertEquals(vals[a][b], dum.get(a, b), 10e-9);
                    }
                }
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    @Test
    @Override
    public void testSerialisation() throws IOException {
        super.testSerialisation();
    }

    @Override
    public DoubleUpperMatrix createInstance() {
        return new DoubleUpperMatrix(0x00);
    }

    /**
     *
     * @return
     */
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