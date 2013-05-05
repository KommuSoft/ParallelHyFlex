package parallelhyflex.algebra;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author kommusoft
 */
public class WeakFleightWeight<TOrigin, Type> extends FleightWeightBase<TOrigin, Type> {

    private final HashMap<TOrigin, WeakReference<Type>> map = new HashMap<>();

    public WeakFleightWeight(Generator<TOrigin, Type> generator) {
        super(generator);
    }

    @Override
    public boolean inCache(TOrigin origin) {
        if (this.map.containsKey(origin)) {
            if (this.map.get(origin).get() == null) {
                this.map.remove(origin);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private Type addAndReturn(TOrigin origin) {
        Type val = this.getGenerator().generate(origin);
        this.map.put(origin, new WeakReference<>(val));
        return val;
    }

    @Override
    public void reduce() {
        for (Iterator<Entry<TOrigin, WeakReference<Type>>> it = this.map.entrySet().iterator(); it.hasNext();) {
            Entry<TOrigin, WeakReference<Type>> pairs = it.next();
            if(pairs.getValue().get() == null) {
                it.remove();
            }
        }
    }

    @Override
    public Type generate(TOrigin origin) {
        if (this.map.containsKey(origin)) {
            Type val = this.map.get(origin).get();
            if (val == null) {
                return addAndReturn(origin);
            } else {
                return val;
            }
        } else {
            return addAndReturn(origin);
        }
    }
}
