/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class ArrayIterable<T> implements Iterable<T> {

    private static final Logger LOG = Logger.getLogger(ArrayIterable.class.getName());
    private final T[] list;

    /**
     *
     * @param list
     */
    public ArrayIterable(T[] list) {
        this.list = list;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(list);
    }
}
