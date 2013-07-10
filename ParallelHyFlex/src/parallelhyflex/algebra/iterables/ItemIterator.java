package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ItemIterator<T> implements Iterator<T> {
    
    private T value;
    
    /**
     *
     * @param value
     */
    public ItemIterator (T value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return value != null;
    }

    /**
     *
     * @return
     */
    @Override
    public T next() {
        T res = value;
        value = null;
        return res;
    }

    /**
     *
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from an ItemIterable!");
    }
    private static final Logger LOG = Logger.getLogger(ItemIterator.class.getName());
    
}
