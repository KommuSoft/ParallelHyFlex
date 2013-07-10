package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class EmptyIterator<T> implements Iterator<T> {

    private static final Logger LOG = Logger.getLogger(EmptyIterator.class.getName());

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Nothing to remove from an EmptyIterator.");
    }
}
