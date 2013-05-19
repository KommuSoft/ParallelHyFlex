package parallelhyflex.algebra.collections;

/**
 *
 * @author kommusoft
 */
public class CastingIterable<TFrom, TTo extends TFrom> implements Iterable<TTo> {

    private final Iterable<TFrom> baseIterable;

    public CastingIterable(Iterable<TFrom> baseIterable) {
        this.baseIterable = baseIterable;
    }

    @Override
    public CastingIterator<TFrom, TTo> iterator() {
        return new CastingIterator<>(this.baseIterable.iterator());
    }
}
