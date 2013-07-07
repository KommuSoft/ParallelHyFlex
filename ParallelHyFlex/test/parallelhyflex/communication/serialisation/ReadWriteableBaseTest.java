package parallelhyflex.communication.serialisation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.Assert;
import parallelhyflex.TestParameters;

/**
 *
 * @author kommusoft
 */
public abstract class ReadWriteableBaseTest<T extends ReadWriteable> {

    public ReadWriteableBaseTest() {
    }

    public void testSerialisation() throws IOException {
        for (int i = 0x00; i < TestParameters.LOOP_PARAMETER; i++) {
            T instance1 = this.createRandomInstance();
            T instance2 = this.createInstance();
            byte[] data;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                try (DataOutputStream dos = new DataOutputStream(baos)) {
                    instance1.write(dos);
                }
                data = baos.toByteArray();
            }
            try (ByteArrayInputStream bais = new ByteArrayInputStream(data); DataInputStream dis = new DataInputStream(bais)) {
                instance2.read(dis);
                Assert.assertEquals(instance1, instance2);
            }
        }
    }

    public abstract T createRandomInstance();

    public T createInstance() {
        return this.createRandomInstance();
    }
}