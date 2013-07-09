package parallelhyflex.algebra;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import parallelhyflex.TestParameters;

/**
 *
 * @author kommusoft
 */
public class UpperMatrixBaseTest {

    /**
     *
     */
    @Test
    public void testCalculateOrderedIndex() {
        for (int n = 0x00; n < TestParameters.LOOP_PARAMETER; n++) {
            UpperMatrixBaseImpl um = new UpperMatrixBaseImpl(n);
            int k = 0x00;
            for (int i = 0x00; i < n; i++) {
                for (int j = i + 0x01; j < n; j++) {
                    Assert.assertEquals(String.format("(%s,%s,%s)",n,i,j),k, um.calculateOrderedIndex(i, j));
                    k++;
                }
            }
        }
    }

    /**
     *
     */
    @Test
    public void testCalculateSize() {
        UpperMatrixBaseImpl um = new UpperMatrixBaseImpl(0x00);
        for (int i = 0x00; i < TestParameters.LOOP_PARAMETER; i++) {
            Assert.assertEquals((i * i - i) / 2, um.calculateSize(i));
        }
    }

    /**
     *
     */
    public class UpperMatrixBaseImpl extends UpperMatrixBase {

        /**
         *
         * @param n
         */
        public UpperMatrixBaseImpl(int n) {
            super(n);
        }

        /**
         *
         * @param i
         * @param j
         * @return
         */
        @Override
        public Object get(int i, int j) {
            return null;
        }

        /**
         *
         * @param i
         * @param j
         * @param value
         */
        @Override
        public void set(int i, int j, Object value) {
        }

        /**
         *
         * @param dis
         * @throws IOException
         */
        @Override
        public void read(DataInputStream dis) throws IOException {
        }

        /**
         *
         * @param dos
         * @throws IOException
         */
        @Override
        public void write(DataOutputStream dos) throws IOException {
        }
    }
}