/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author kommusoft
 */
public class Utils {

    private Utils() {
    }

    public static int getLengthIndex(int[] cdfI, int index) {
        int i = Arrays.binarySearch(cdfI, index);
        if (i < 0) {
            i = ~i;
        }
        return i;
    }

    public static int countOnes(long data) {
        int ones = 0;
        while (data != 0) {
            ones += data & 1;
            data >>= 1;
        }
        return ones;
    }

    public static boolean arrayEquality(long[] a, long[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
    public static final Random StaticRandom = new Random();

    public static String stringReverse(String inp) {
        StringBuilder sb = new StringBuilder();
        for (int i = inp.length() - 1; i >= 0; i--) {
            sb.append(inp.charAt(i));
        }
        return sb.toString();
    }

    public static <T> boolean arrayEquality(T[] a, T... b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

    public static Iterator<Integer> getLimitedModuloEnumerable(int offset, int delta, int modulo) {
        return new Iterator<Integer>() {
            private int modulo;
            private int value = 0;
            private int offset;
            private int delta;

            @Override
            public boolean hasNext() {
                return value < modulo;
            }

            @Override
            public Integer next() {
                int val = this.value;
                this.value += delta;
                return (this.value + offset) % modulo;
            }

            public Iterator<Integer> setValues(int modulo, int delta, int offset) {
                this.modulo = modulo;
                this.delta = delta;
                this.offset = offset;
                return this;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove from a virtual list.");
            }
        }.setValues(offset, delta, modulo);
    }

    public static Iterator<Integer> getLimitedModuloEnumerable(int offset, int modulo) {
        return getLimitedModuloEnumerable(offset, 1, modulo);
    }
    public static<T> Collection<T> fromIterator (Iterator<T> iterator) {
        return new Collection<T>() {
            
            private Iterator<T> iterator;

            @Override
            public int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEmpty() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Iterator<T> iterator() {
                return this.iterator;
            }

            @Override
            public Object[] toArray() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <T> T[] toArray(T[] a) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean add(T e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean addAll(Collection<? extends T> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            private Collection<T> setIterator (Iterator<T> iterator) {
                this.iterator = iterator;
                return this;
            }
            
        }.setIterator(iterator);
    }
}
