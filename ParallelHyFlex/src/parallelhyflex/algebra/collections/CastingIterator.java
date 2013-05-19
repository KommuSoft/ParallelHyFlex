package parallelhyflex.algebra.collections;

import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class CastingIterator<TFrom, TTo extends TFrom> implements Iterator<TTo> {

    private final Iterator<TFrom> baseIterator;

    public CastingIterator(Iterator<TFrom> baseIterator) {
        this.baseIterator = baseIterator;
    }

    @Override
    public boolean hasNext() {
        return this.baseIterator.hasNext();
    }

    @Override
    public TTo next() {
        return (TTo) this.baseIterator.next();
    }

    @Override
    public void remove() {
        this.baseIterator.remove();
    }
}
