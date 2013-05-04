package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class ItemIterable<T> implements Iterable<T> {
    
    private final T value;
    
    public ItemIterable (T value) {
        this.value = value;
    }

    @Override
    public Iterator<T> iterator() {
        return new ItemIterator(this.value);
    }
    
}
