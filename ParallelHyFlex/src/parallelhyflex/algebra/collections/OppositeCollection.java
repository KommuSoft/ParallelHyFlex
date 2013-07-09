package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class OppositeCollection<T> implements Collection<T> {

    private final Collection<T> innerCollection;

    /**
     *
     * @param collection
     */
    public OppositeCollection(Collection<T> collection) {
        this.innerCollection = collection;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return Integer.MAX_VALUE;
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
        return !this.innerCollection.contains(o);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Cannot iterate over an opposite collection.");
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Cannot generate an array of opposite elements.");
    }

    /**
     *
     * @param <T>
     * @param ts
     * @return
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Cannot generate an array of opposite elements.");
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(T e) {
        return !this.innerCollection.remove(e);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        try {
            T t = (T) o;
            return !this.innerCollection.add((T) o);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object o : clctn) {
            if (this.contains(o)) {
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
        boolean ch = false;
        for (T t : clctn) {
            ch |= this.add(t);
        }
        return ch;
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        boolean ch = false;
        for (Object o : clctn) {
            ch |= this.remove(o);
        }
        return ch;
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Cannot retain in an opposite collection.");
    }

    /**
     *
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear an opposite collection.");
    }
}
