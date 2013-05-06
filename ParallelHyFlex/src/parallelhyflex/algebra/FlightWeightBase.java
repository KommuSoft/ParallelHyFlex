package parallelhyflex.algebra;

public abstract class FlightWeightBase<TOrigin, Type> implements FlightWeight<TOrigin, Type> {

    private final Generator<TOrigin, Type> generator;

    protected FlightWeightBase(Generator<TOrigin, Type> generator) {
        this.generator = generator;
    }

    @Override
    public Generator<TOrigin, Type> getGenerator() {
        return this.generator;
    }

    @Override
    public void reduce() {
    }
}
