package parallelhyflex.algebra;

public abstract class FleightWeightBase<TOrigin, Type> implements FleightWeight<TOrigin, Type> {

    private final Generator<TOrigin, Type> generator;

    protected FleightWeightBase(Generator<TOrigin, Type> generator) {
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
