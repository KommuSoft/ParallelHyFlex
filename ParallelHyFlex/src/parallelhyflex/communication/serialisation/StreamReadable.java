package parallelhyflex.communication.serialisation;

import java.io.InputStream;

/**
 *
 * @author kommusoft
 */
public interface StreamReadable {

    void readStream(InputStream stream);

    void readFile(String filename);
}
