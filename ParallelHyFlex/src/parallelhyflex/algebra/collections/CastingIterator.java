package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class CastingIterator<TFrom, TTo extends TFrom> implements Iterator<TTo> {

    private final Iterator<TFrom> baseIterator;

    /**
     *
     * @param baseIterator
     */
    public CastingIterator(Iterator<TFrom> baseIterator) {
        this.baseIterator = baseIterator;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return this.baseIterator.hasNext();
    }

    /**
     *
     * @return
     */
    @Override
    public TTo next() {
        return (TTo) this.baseIterator.next();
    }

    /**
     *
     */
    @Override
    public void remove() {
        this.baseIterator.remove();
    }
}
