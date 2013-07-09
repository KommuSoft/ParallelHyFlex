package parallelhyflex.algebra.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class ArrayToListWrapper<T> implements List<T>, Generator<Integer, T> {

    private final T[] array;

    /**
     *
     * @param array
     */
    public ArrayToListWrapper(T[] array) {
        this.array = array;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return array.length;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size() <= 0;
    }

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator(this.array);
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        Object[] objs = new Object[this.size()];
        System.arraycopy(this.array, 0, objs, 0, this.array.length);
        return objs;
    }

    /**
     *
     * @param <TA>
     * @param a
     * @return
     */
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

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("Cannot add elements in an array!");
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Cannot add elements in an array!");
    }

    /**
     *
     * @param index
     * @param c
     * @return
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Cannot add elements in an array!");
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot retain elements in an array!");
    }

    /**
     *
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear an array!");
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        return this.array[index];
    }

    /**
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        T old = this.array[index];
        this.array[index] = element;
        return old;
    }

    /**
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Cannot remove add in an array!");
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Cannot remove elements in an array!");
    }

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param fromIndex
     * @param toIndex
     * @return
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public T generate(Integer variable) {
        return this.array[variable];
    }
}
