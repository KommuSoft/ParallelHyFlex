package parallelhyflex.communication.serialisation;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public interface Readable {

    void read(DataInputStream dis) throws IOException;
}
