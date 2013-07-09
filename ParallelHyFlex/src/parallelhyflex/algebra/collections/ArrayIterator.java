package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class ArrayIterator<T> implements Iterator<T> {

    private final T[] array;
    private int index = -1;

    /**
     *
     * @param array
     */
    public ArrayIterator(T... array) {
        this.array = array;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return this.index < this.array.length - 1;
    }

    /**
     *
     * @return
     */
    @Override
    public T next() {
        return this.array[++this.index];
    }

    /**
     *
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }
}
