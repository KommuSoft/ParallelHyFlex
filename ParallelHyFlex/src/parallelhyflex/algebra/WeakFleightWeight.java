package parallelhyflex.algebra;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author kommusoft
 */
public class WeakFleightWeight<TOrigin, Type> extends FlightWeightBase<TOrigin, Type> {

    private final HashMap<TOrigin, WeakReference<Type>> map = new HashMap<>();

    /**
     *
     * @param generator
     */
    public WeakFleightWeight(Generator<TOrigin, Type> generator) {
        super(generator);
    }

    /**
     *
     * @param origin
     * @return
     */
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

    /**
     *
     */
    @Override
    public void reduce() {
        for (Iterator<Entry<TOrigin, WeakReference<Type>>> it = this.map.entrySet().iterator(); it.hasNext();) {
            Entry<TOrigin, WeakReference<Type>> pairs = it.next();
            if(pairs.getValue().get() == null) {
                it.remove();
            }
        }
    }

    /**
     *
     * @param origin
     * @return
     */
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
        return new WeakFlightWeightIterator();
    }
    
    private class WeakFlightWeightIterator implements Iterator<Type> {
        
        private final Iterator<WeakReference<Type>> valueIterator;
        private Type last;
        
        WeakFlightWeightIterator () {
            this.valueIterator = map.values().iterator();
        }

        @Override
        public boolean hasNext() {
            while(last == null && valueIterator.hasNext()) {
                last = valueIterator.next().get();
                if(last == null) {
                    valueIterator.remove();
                }
            }
            return last != null;
        }

        @Override
        public Type next() {
            if(last == null) {
                this.hasNext();
            }
            Type ret = last;
            last = null;
            return last;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
