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
public class EmptyIterable<T> implements Iterable<T> {

    private static final Logger LOG = Logger.getLogger(EmptyIterable.class.getName());

    @Override
    public Iterator<T> iterator() {
        return new EmptyIterator<>();
    }
}
