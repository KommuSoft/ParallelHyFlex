package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class ConstantInfiniteCollection<T> implements Collection<T> {

    private final T data;

    public ConstantInfiniteCollection(T data) {
        this.data = data;
    }

    @Override
    public int size() {
        return -0x01;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return o.equals(this.data);
    }

    @Override
    public Iterator<T> iterator() {
        return new ConstantInfiniteIterator<T>(this.data);
    }

    @Override
    public Object[] toArray() {
        return new Object[] {this.data};
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(T e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object o : clctn) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        return false;
    }

    @Override
    public void clear() {
    }
    
}
