package parallelhyflex.algebra.collections.iterables;

import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class ComparatorHeadTailIterator<T> extends HeadTailIterator<T> implements Comparable<HeadTailIterator<T>> {

    private static final Logger LOG = Logger.getLogger(ComparableHeadTailIterator.class.getName());
    private final Comparator<T> comparator;

    public ComparatorHeadTailIterator(Comparator<T> comparator, Iterator<T> iterator) {
        super(iterator);
        this.comparator = comparator;
    }

    public ComparatorHeadTailIterator(Comparator<T> comparator, Iterable<T> iterable) {
        super(iterable);
        this.comparator = comparator;
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(HeadTailIterator<T> other) {
        return this.comparator.compare(this.head(), other.head());
    }
}
