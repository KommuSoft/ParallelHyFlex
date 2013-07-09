package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 * @param <TOrigin>
 * @param <Type>
 */
public abstract class FlightWeightBase<TOrigin, Type> implements FlightWeight<TOrigin, Type> {

    private final Generator<TOrigin, Type> generator;

    /**
     *
     * @param generator
     */
    protected FlightWeightBase(Generator<TOrigin, Type> generator) {
        this.generator = generator;
    }

    /**
     *
     * @return
     */
    @Override
    public Generator<TOrigin, Type> getGenerator() {
        return this.generator;
    }

    /**
     *
     */
    @Override
    public void reduce() {
    }
}
