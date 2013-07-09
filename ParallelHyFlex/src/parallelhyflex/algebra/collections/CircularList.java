package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

/**
 *
 * @author kommusoft
 */
public class CircularList<TItem> implements List<TItem>, Queue<TItem> {

    private int readPointer;
    private int writePointer;
    private Object[] data;

    /**
     *
     * @param capacity
     */
    public CircularList(int capacity) {
        this.data = new Object[capacity];
        this.readPointer = 0;
        this.writePointer = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.data.length;
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
        if (o == null) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            if (o.equals(this.data[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<TItem> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        Object[] obj = new Object[data.length];
        System.arraycopy(this.data, this.readPointer, obj, 0, this.data.length - this.readPointer);
        System.arraycopy(this.data, 0, obj, this.data.length - this.readPointer, this.readPointer);
        return obj;
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
    public boolean add(TItem e) {
        this.data[writePointer] = e;
        this.incWritePointer();
        return true;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        return false;//cannot directly remove
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
    public boolean addAll(Collection<? extends TItem> clctn) {
        for (TItem t : clctn) {
            this.add(t);
        }
        return true;
    }

    /**
     *
     * @param i
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(int i, Collection<? extends TItem> clctn) {
        return false;//cannot insert
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        return false;//cannot remove given collections
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        return false;//cannot retain given collections
    }

    /**
     *
     */
    @Override
    public void clear() {
        this.writePointer = 0;
        this.readPointer = 0;
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public TItem get(int i) {
        return (TItem) this.data[relReadPointer(i)];
    }

    /**
     *
     * @param i
     * @param e
     * @return
     */
    @Override
    public TItem set(int i, TItem e) {
        int index = relWritePointer(i);
        TItem res = (TItem) this.data[index];
        this.data[index] = e;
        return res;
    }

    /**
     *
     * @param i
     * @param e
     */
    @Override
    public void add(int i, TItem e) {
        this.set(i, e);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public TItem remove(int i) {
        return this.set(i, null);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < data.length-this.readPointer; i++) {
                if (o.equals(data[i+this.readPointer])) {
                    return i;
                }
            }
            for (int i = -this.readPointer; i < 0; i++) {
                if (o.equals(data[i+this.readPointer])) {
                    return i+data.length;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            for (int i = -1; i >= -this.readPointer; i--) {
                if (o.equals(data[i+this.readPointer])) {
                    return i+data.length;
                }
            }
            for (int i = data.length-this.readPointer-1; i > 0; i--) {
                if (o.equals(data[i+this.readPointer])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @return
     */
    @Override
    public ListIterator<TItem> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public ListIterator<TItem> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public List<TItem> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean offer(TItem e) {
        this.data[this.writePointer] = e;
        this.incWritePointer();
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public TItem remove() {
        TItem item = this.element();
        this.incReadPointer();
        return item;
    }

    /**
     *
     * @return
     */
    @Override
    public TItem poll() {
        return this.remove();
    }

    /**
     *
     * @return
     */
    @Override
    public TItem element() {
        return (TItem) this.data[this.readPointer];
    }

    /**
     *
     * @return
     */
    @Override
    public TItem peek() {
        return this.element();
    }

    private int relReadPointer(int i) {
        return (this.readPointer + i) % this.data.length;
    }
    private int relWritePointer(int i) {
        return (this.writePointer + i) % this.data.length;
    }
    private void incReadPointer () {
        this.readPointer = relReadPointer(1);
    }
    private void incWritePointer () {
        this.readPointer = relReadPointer(1);
    }
    
}
