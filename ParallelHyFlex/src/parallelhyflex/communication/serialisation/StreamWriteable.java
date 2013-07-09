package parallelhyflex.communication.serialisation;

import java.io.OutputStream;

/**
 *
 * @author kommusoft
 */
public interface StreamWriteable {

    void writeStream(OutputStream stream);

    void writeFile(String filename);
}
