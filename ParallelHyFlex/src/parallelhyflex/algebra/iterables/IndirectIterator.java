package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class IndirectIterator<TItem> implements Iterator<TItem> {

    private final Iterator<? extends Iterable<TItem>> realIterator;
    private Iterator<TItem> subIterator;

    /**
     *
     * @param realIterator
     */
    public IndirectIterator(Iterator<? extends Iterable<TItem>> realIterator) {
        this.realIterator = realIterator;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        if (subIterator == null) {
            return loadNext();
        } else if (!subIterator.hasNext()) {
            return loadNext();
        } else {
            return true;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public TItem next() {
        hasNext();
        return subIterator.next();
    }

    /**
     *
     */
    @Override
    public void remove() {
        if (this.subIterator != null) {
            this.subIterator.remove();
        }
    }

    private boolean loadNext() {
        while ((subIterator == null || !subIterator.hasNext()) && realIterator.hasNext()) {
            subIterator = realIterator.next().iterator();
        }
        return (subIterator != null && subIterator.hasNext());
    }
    private static final Logger LOG = Logger.getLogger(IndirectIterator.class.getName());
}
