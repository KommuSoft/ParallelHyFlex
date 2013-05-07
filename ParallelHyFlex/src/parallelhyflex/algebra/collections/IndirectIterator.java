package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class IndirectIterator<TItem> implements Iterator<TItem> {

    private final Iterator<? extends Iterable<TItem>> realIterator;
    private Iterator<TItem> subIterator;

    public IndirectIterator(Iterator<? extends Iterable<TItem>> realIterator) {
        this.realIterator = realIterator;
    }

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

    @Override
    public TItem next() {
        hasNext();
        return subIterator.next();
    }

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
}
