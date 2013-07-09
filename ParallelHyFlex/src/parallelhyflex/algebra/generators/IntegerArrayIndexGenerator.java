package parallelhyflex.algebra.generators;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class IntegerArrayIndexGenerator implements Generator<Integer, Integer>, List<Integer> {

    private final int[] array;

    /**
     *
     * @param array
     */
    public IntegerArrayIndexGenerator(int[] array) {
        this.array = array;
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public Integer generate(Integer variable) {
        return array[variable];
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.array.length;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size() > 0x00;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0x00; i < this.array.length; i++) {
            if (o.equals(array[i])) {
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
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        int n = this.array.length;
        Object[] copy = new Object[n];
        for (int i = 0x00; i < n; i++) {
            copy[i] = this.array[i];
        }
        return copy;
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
    public boolean add(Integer e) {
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
        for (Object x : clctn) {
            if (!this.contains(x)) {
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
    public boolean addAll(Collection<? extends Integer> clctn) {
        return false;
    }

    /**
     *
     * @param i
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(int i, Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void clear() {
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Integer get(int i) {
        return this.array[i];
    }

    /**
     *
     * @param i
     * @param e
     * @return
     */
    @Override
    public Integer set(int i, Integer e) {
        Integer val = this.array[i];
        this.array[i] = e;
        return val;
    }

    /**
     *
     * @param i
     * @param e
     */
    @Override
    public void add(int i, Integer e) {
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Integer remove(int i) {
        return this.array[i];
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        int n = this.array.length;
        for (int i = 0x00; i < n; i++) {
            if (o.equals(this.array[i])) {
                return i;
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
        int n = this.array.length;
        for (int i = n - 0x01; i >= 0x00; i--) {
            if (o.equals(this.array[i])) {
                return i;
            }
        }
        return -0x01;
    }

    /**
     *
     * @return
     */
    @Override
    public ListIterator<Integer> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public ListIterator<Integer> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public List<Integer> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}