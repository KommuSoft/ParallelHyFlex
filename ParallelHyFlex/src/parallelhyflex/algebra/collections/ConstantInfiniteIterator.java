package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class ConstantInfiniteIterator<T> implements Iterator<T> {

    private final T data;

    /**
     *
     * @param data
     */
    public ConstantInfiniteIterator(T data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public T next() {
        return this.data;
    }

    /**
     *
     */
    @Override
    public void remove() {
    }
}
