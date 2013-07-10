package parallelhyflex.algebra.improvement;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class SingleFixedImprovementList<T> implements FixedImprovementList<T>, Serializable {

    private static final Logger LOG = Logger.getLogger(FixedImprovementList.class.getName());
    private static final long serialVersionUID = 1L;
    private Object[] values;
    private int index;

    /**
     *
     * @param length
     * @param initvalue
     */
    public SingleFixedImprovementList(int length, T... initvalue) {
        this.values = new Object[length];
        reset(initvalue[0]);
        this.addImprovements(initvalue);
    }

    @Override
    public void reset(T initvalue) {
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = initvalue;
        }
        this.index = 0;
    }

    @Override
    public void addImprovement(T improvement) {
        index = (index + 1) % this.values.length;
        this.values[index] = improvement;
    }
    
    @Override
    public void addImprovements(T... improvements) {
        for(T t : improvements) {
            this.addImprovement(t);
        }
    }

    @Override
    public T getHistoryElement(int generations) {
        if (generations >= values.length || generations < 0x00) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.values[(this.index + this.values.length - generations) % this.values.length];
    }

    @Override
    public T getTop() {
        return (T) this.values[index];
    }

    @Override
    public int size() {
        return this.values.length;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new SingleFixedImprovementListIterator();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < index; i++) {
            sb.append(values[i]);
            sb.append(' ');
        }
        sb.append('>');
        for (int i = index; i < values.length; i++) {
            sb.append(values[i]);
            sb.append(' ');
        }
        sb.append(']');
        return sb.toString();
    }

    private class SingleFixedImprovementListIterator implements Iterator<T> {

        int add = values.length;

        @Override
        public boolean hasNext() {
            return add > 0;
        }

        @Override
        public T next() {
            T val = (T) values[(index + add) % values.length];
            add--;
            return val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported for FixedImprovementLists.");
        }
    }
}
