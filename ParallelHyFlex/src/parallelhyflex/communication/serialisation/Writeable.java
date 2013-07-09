package parallelhyflex.communication.serialisation;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public interface Writeable {

    void write(DataOutputStream dos) throws IOException;
}
