package parallelhyflex.utils;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
interface UniqueRandomGenerator<T> extends Iterator<T>, Iterable<T> {

    /**
     *
     */
    void reset();
}
