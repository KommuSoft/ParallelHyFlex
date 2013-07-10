package parallelhyflex.algebra.collections.iterables;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class HeadTailIteratorComparator<T> implements Comparator<HeadTailIterator<T>> {

    private final Comparator<T> comparator;

    /**
     *
     * @param comparator
     */
    public HeadTailIteratorComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(HeadTailIterator<T> o1, HeadTailIterator<T> o2) {
        return this.comparator.compare(o1.head(), o2.head());
    }
    private static final Logger LOG = Logger.getLogger(HeadTailIteratorComparator.class.getName());
}
