package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class ConstantInfiniteCollection<T> implements Collection<T> {

    private final T data;

    /**
     *
     * @param data
     */
    public ConstantInfiniteCollection(T data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return -0x01;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return o.equals(this.data);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ConstantInfiniteIterator<>(this.data);
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        return new Object[] {this.data};
    }

    /**
     *
     * @param <T>
     * @param ts
     * @return
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(T e) {
        return false;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object o : clctn) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        return false;
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        return false;
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        return false;
    }

    /**
     *
     */
    @Override
    public void clear() {
    }
    
}
