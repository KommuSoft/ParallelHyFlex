package parallelhyflex.communication;

import java.io.DataInputStream;

/**
 *
 * @author kommusoft
 */
public interface ReadableGenerator<TObject> {
    
    public TObject readAndGenerate (DataInputStream dis);
    
}
