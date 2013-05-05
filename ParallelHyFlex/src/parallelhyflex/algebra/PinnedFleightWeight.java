package parallelhyflex.algebra;

import java.util.HashMap;

/**
 *
 * @author kommusoft
 */
public class PinnedFleightWeight<TOrigin, Type> extends FleightWeightBase<TOrigin, Type> {

    private final HashMap<TOrigin, Type> map = new HashMap<>();

    public PinnedFleightWeight(Generator<TOrigin, Type> generator) {
        super(generator);
    }

    @Override
    public boolean inCache(TOrigin origin) {
        return this.map.containsKey(origin);
    }

    @Override
    public Type generate(TOrigin origin) {
        if (this.map.containsKey(origin)) {
            return this.map.get(origin);
        } else {
            Type val = this.getGenerator().generate(origin);
            this.map.put(origin, val);
            return val;
        }
    }
}
