package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public interface ArgumentIterable<TArgument,TItem> {
    
    Iterator<TItem> iterator (TArgument argument);
    
}
