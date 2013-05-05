package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class OppositeCollection<T> implements Collection<T> {

    private final Collection<T> innerCollection;

    public OppositeCollection(Collection<T> collection) {
        this.innerCollection = collection;
    }

    @Override
    public int size() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return !this.innerCollection.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Cannot iterate over an opposite collection.");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Cannot generate an array of opposite elements.");
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Cannot generate an array of opposite elements.");
    }

    @Override
    public boolean add(T e) {
        return !this.innerCollection.remove(e);
    }

    @Override
    public boolean remove(Object o) {
        try {
            T t = (T) o;
            return !this.innerCollection.add((T) o);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object o : clctn) {
            if (this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        boolean ch = false;
        for (T t : clctn) {
            ch |= this.add(t);
        }
        return ch;
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        boolean ch = false;
        for (Object o : clctn) {
            ch |= this.remove(o);
        }
        return ch;
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Cannot retain in an opposite collection.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear an opposite collection.");
    }
}
