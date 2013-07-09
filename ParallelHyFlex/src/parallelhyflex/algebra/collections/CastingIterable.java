package parallelhyflex.algebra.collections;

/**
 *
 * @author kommusoft
 */
public class CastingIterable<TFrom, TTo extends TFrom> implements Iterable<TTo> {

    private final Iterable<TFrom> baseIterable;

    /**
     *
     * @param baseIterable
     */
    public CastingIterable(Iterable<TFrom> baseIterable) {
        this.baseIterable = baseIterable;
    }

    /**
     *
     * @return
     */
    @Override
    public CastingIterator<TFrom, TTo> iterator() {
        return new CastingIterator<>(this.baseIterable.iterator());
    }
}
