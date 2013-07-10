package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ItemIterable<T> implements Iterable<T> {
    
    private final T value;
    
    /**
     *
     * @param value
     */
    public ItemIterable (T value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ItemIterator(this.value);
    }
    private static final Logger LOG = Logger.getLogger(ItemIterable.class.getName());
    
}
