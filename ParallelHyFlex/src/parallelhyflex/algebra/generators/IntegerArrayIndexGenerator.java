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

    public IntegerArrayIndexGenerator(int[] array) {
        this.array = array;
    }

    @Override
    public Integer generate(Integer variable) {
        return array[variable];
    }

    @Override
    public int size() {
        return this.array.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size() > 0x00;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0x00; i < this.array.length; i++) {
            if (o.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        int n = this.array.length;
        Object[] copy = new Object[n];
        for (int i = 0x00; i < n; i++) {
            copy[i] = this.array[i];
        }
        return copy;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Integer e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object x : clctn) {
            if (!this.contains(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> clctn) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Integer> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
    }

    @Override
    public Integer get(int i) {
        return this.array[i];
    }

    @Override
    public Integer set(int i, Integer e) {
        Integer val = this.array[i];
        this.array[i] = e;
        return val;
    }

    @Override
    public void add(int i, Integer e) {
    }

    @Override
    public Integer remove(int i) {
        return this.array[i];
    }

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

    @Override
    public ListIterator<Integer> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<Integer> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}