package parallelhyflex.algebra.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author kommusoft
 */
public class ArrayToListWrapper<T> implements List<T> {

    private final T[] array;

    public ArrayToListWrapper(T[] array) {
        this.array = array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size() <= 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o != null) {
            for (T t : array) {
                if (o.equals(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator(this.array);
    }

    @Override
    public Object[] toArray() {
        Object[] objs = new Object[this.size()];
        System.arraycopy(this.array, 0, objs, 0, this.array.length);
        return objs;
    }

    @Override
    public <TA> TA[] toArray(TA[] a) {
        if (a.length < this.size()) {
            return (TA[]) Arrays.copyOf(this.array, this.size(), a.getClass());
        }
        System.arraycopy(this.array, 0, a, 0, this.size());
        if (a.length > this.size()) {
            a[this.size()] = null;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("Cannot add elements in an array!");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Cannot add elements in an array!");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Cannot add elements in an array!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot retain elements in an array!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear an array!");
    }

    @Override
    public T get(int index) {
        return this.array[index];
    }

    @Override
    public T set(int index, T element) {
        T old = this.array[index];
        this.array[index] = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Cannot remove add in an array!");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }

    @Override
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < this.array.length; i++) {
                if (o.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -0x01;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            for (int i = this.array.length - 1; i >= 0; i--) {
                if (o.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -0x01;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
