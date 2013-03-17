package parallelhyflex.communication;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public interface ReadableGenerator<TObject> {
    
    public TObject readAndGenerate (DataInputStream dis) throws IOException;
    
}
