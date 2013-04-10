package parallelhyflex.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.tuples.Tuple2;

/**
 *
 * @author kommusoft
 */
public class Utils {

    public static double border(double min, double val, double max) {
        return Math.min(Math.min(max, val),max);
    }

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
            data >>>= 1;
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

    public static Iterable<Integer> getLimitedModuloEnumerable(int offset, int delta, int modulo) {
        return new Iterable<Integer>() {
            private int offset, delta, modulo;

            @Override
            public Iterator<Integer> iterator() {
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
                        return (val + offset) % modulo;
                    }

                    public Iterator<Integer> setValues(int offset, int delta, int modulo) {
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

            private Iterable<Integer> setValues(int offset, int delta, int modulo) {
                this.offset = offset;
                this.delta = delta;
                this.modulo = modulo;
                return this;
            }
        }.setValues(offset, delta, modulo);
    }

    public static Iterable<Integer> getLimitedModuloEnumerable(int offset, int modulo) {
        return getLimitedModuloEnumerable(offset, 1, modulo);
    }

    public static <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }

    public static <T> boolean any(Iterable<T> collection, Generator<T, Boolean> predicate) {
        for (T t : collection) {
            if (predicate.generate(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean all(Iterable<T> collection, Generator<T, Boolean> predicate) {
        for (T t : collection) {
            if (!predicate.generate(t)) {
                return false;
            }
        }
        return true;
    }
    
    public static<TFrom,TTo> HashMap<TFrom,TTo> generateMapping (Collection<TFrom> collection, Generator<TFrom,TTo> generator) {
        HashMap<TFrom,TTo> mapping = new HashMap<>();
        for(TFrom from : collection) {
            mapping.put(from,generator.generate(from));
        }
        return mapping;
    }
    public static<TFrom,TTo> ArrayList<TTo> generateMappedArrayList (Iterable<TFrom> iterable, Generator<TFrom,TTo> generator) {
        ArrayList<TTo> result = new ArrayList<TTo>();
        for(TFrom x : iterable) {
            result.add(generator.generate(x));
        }
        return result;
    }
    
    public static <T> T Fold (Generator<Tuple2<? extends T, ? extends T>,? extends T> function, Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if(it.hasNext()) {
            T temp = it.next();
            while (it.hasNext()) {
                temp = function.generate(new Tuple2<>(temp,it.next()));

            }
            return temp;
        }
        else {
            return null;
        }
    }
    
}
