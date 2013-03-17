package parallelhyflex.communication;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public interface Writable {
    
    void write (DataOutputStream dos) throws IOException;
    
}
