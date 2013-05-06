package parallelhyflex.algebra;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class PinnedFlightWeight<TOrigin, Type> extends FlightWeightBase<TOrigin, Type> {

    private final HashMap<TOrigin, Type> map = new HashMap<>();

    public PinnedFlightWeight(Generator<TOrigin, Type> generator) {
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

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Iterator<Type> iterator() {
        return this.map.values().iterator();
    }
}
