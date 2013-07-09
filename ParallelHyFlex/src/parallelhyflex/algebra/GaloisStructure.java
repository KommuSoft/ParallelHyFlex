package parallelhyflex.algebra;

/**
 *
 * @param <TConcrete>
 * @param <TAbstract>
 * @author kommusoft
 */
public class GaloisStructure<TConcrete, TAbstract> {

    private final Generator<TConcrete, TAbstract> alpha;
    private final Generator<TAbstract, TConcrete> gamma;

    public GaloisStructure(Generator<TConcrete, TAbstract> alpha, Generator<TAbstract, TConcrete> gamma) {
        this.alpha = alpha;
        this.gamma = gamma;
    }

    /**
     * @return the alpha
     */
    public Generator<TConcrete, TAbstract> getAlpha() {
        return alpha;
    }

    /**
     * @return the gamma
     */
    public Generator<TAbstract, TConcrete> getGamma() {
        return gamma;
    }
}
