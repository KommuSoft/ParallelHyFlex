package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class ConstantInfiniteIterator<T> implements Iterator<T> {

    private final T data;

    public ConstantInfiniteIterator(T data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return this.data;
    }

    @Override
    public void remove() {
    }
}
