package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @param <Q>
 * @author kommusoft
 */
public class ComparableHeadTailIterator<Q, T extends Comparable<Q>> extends HeadTailIterator<T> implements Comparable<HeadTailIterator<Q>> {

    private static final Logger LOG = Logger.getLogger(ComparableHeadTailIterator.class.getName());

    public ComparableHeadTailIterator(Iterator<T> iterator) {
        super(iterator);
    }

    public ComparableHeadTailIterator(Iterable<T> iterable) {
        super(iterable);
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(HeadTailIterator<Q> other) {
        return this.head().compareTo(other.head());
    }
}
