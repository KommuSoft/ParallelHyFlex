package parallelhyflex.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public interface ReadWriteable {
    
    void read (DataInputStream dis) throws IOException;
    void write (DataOutputStream dos) throws IOException;
    
}
