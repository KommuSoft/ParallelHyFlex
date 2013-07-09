package parallelhyflex.utils;

import java.util.ArrayList;

/**
 *
 * @author kommusoft
 */
public class GenericUniqueRandomGenerator<T> implements UniqueRandomGenerator<T> {

    private Object[] values;
    private int ptr = 0x00;

    /**
     *
     * @param generator
     */
    public GenericUniqueRandomGenerator(Iterable<T> generator) {
        ArrayList<T> list = new ArrayList<>();
        for (T t : generator) {
            list.add(t);
        }
        values = list.toArray();
    }

    /**
     *
     * @return
     */
    @Override
    public T next() {
        int i = ptr + Utils.nextInt(values.length - ptr);
        T res = (T) values[i];
        values[i] = values[ptr];
        values[ptr++] = res;
        return res;
    }

    /**
     *
     */
    @Override
    public void reset() {
        this.ptr = 0;
    }

    @Override
    public boolean hasNext() {
        return this.ptr < values.length;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
