package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class ItemIterator<T> implements Iterator<T> {
    
    private T value;
    
    public ItemIterator (T value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        return value != null;
    }

    @Override
    public T next() {
        T res = value;
        value = null;
        return res;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from an ItemIterable!");
    }
    
}
