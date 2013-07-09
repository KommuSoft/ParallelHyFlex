package parallelhyflex.algebra;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class PinnedFlightWeight<TOrigin, Type> extends FlightWeightBase<TOrigin, Type> {

    private final HashMap<TOrigin, Type> map = new HashMap<>();

    /**
     *
     * @param generator
     */
    public PinnedFlightWeight(Generator<TOrigin, Type> generator) {
        super(generator);
    }

    /**
     *
     * @param origin
     * @return
     */
    @Override
    public boolean inCache(TOrigin origin) {
        return this.map.containsKey(origin);
    }

    /**
     *
     * @param origin
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.map.size();
    }

    /**
     *
     */
    @Override
    public void clear() {
        this.map.clear();
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Type> iterator() {
        return this.map.values().iterator();
    }
}
