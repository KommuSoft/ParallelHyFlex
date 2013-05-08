package parallelhyflex.utils;

import java.util.ArrayList;

/**
 *
 * @author kommusoft
 */
public class UniqueRandomGenerator<T> {
    
    private Object[] values;
    private int ptr = 0;
    
    public UniqueRandomGenerator (Iterable<T> generator) {
        ArrayList<T> list = new ArrayList<>();
        for(T t : generator) {
            list.add(t);
        }
        values = list.toArray();
    }
    
    public T next () {
        int i = ptr+Utils.StaticRandom.nextInt(values.length-ptr);
        T res = (T) values[i];
        values[i] = values[ptr];
        values[ptr++] = res;
        return res;
    }
    
    public void reset () {
        this.ptr = 0;
    }
    
}
