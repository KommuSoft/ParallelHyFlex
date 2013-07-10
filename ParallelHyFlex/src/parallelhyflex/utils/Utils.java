package parallelhyflex.utils;

import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.tuples.Tuple2;

/**
 *
 * @author kommusoft
 */
public final class Utils {

    /**
     *
     */
    public static final Random StaticRandom = new Random();
    /**
     *
     */
    public static final double Tolerance = 1e-6;
    /**
     *
     */
    public static final double InvSqrt2 = Math.sqrt(0.5d);

    /**
     *
     * @param index
     * @param ignore
     * @return
     */
    public static int ignoreIndex(int index, int ignore) {
        if (index < ignore) {
            return index;
        } else {
            return index + 0x01;
        }
    }

    /**
     *
     * @param n
     * @param ignore
     * @return
     */
    public static int ignoreRandomIndex(int n, int ignore) {
        return ignoreIndex(nextInt(n - 1), ignore);
    }

    /**
     *
     * @return
     */
    public static int nextInt() {
        return StaticRandom.nextInt();
    }

    public static <T> T iThOrNull(Iterable<T> iterable, int index) {
        return iThOrNull(iterable.iterator(), index);
    }

    /**
     *
     * @param <T>
     * @param iterator
     * @param index
     * @return
     */
    public static <T> T iThOrNull(Iterator<T> iterator, int index) {
        T val = null;
        for (; iterator.hasNext() && index >= 0x00; index--) {
            val = iterator.next();
        }
        if (index < 0x00) {
            return val;
        } else {
            return null;
        }
    }

    /**
     *
     * @param n
     * @return
     */
    public static int nextInt(int n) {
        return StaticRandom.nextInt(n);
    }

    /**
     *
     * @return
     */
    public static long nextLong() {
        return StaticRandom.nextLong();
    }

    /**
     *
     * @return
     */
    public static boolean nextBoolean() {
        return StaticRandom.nextBoolean();
    }

    /**
     *
     * @return
     */
    public static float nextFloat() {
        return StaticRandom.nextFloat();
    }

    /**
     *
     * @return
     */
    public static double nextDouble() {
        return StaticRandom.nextDouble();
    }

    /**
     *
     * @return
     */
    public static double nextGaussian() {
        return StaticRandom.nextGaussian();
    }

    /**
     *
     * @param a
     * @param b
     * @param tolerance
     * @return
     */
    public static boolean isEqualTolerance(double a, double b, double tolerance) {
        return Math.abs(a - b) <= tolerance;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqualTolerance(double a, double b) {
        return isEqualTolerance(a, b, 10e-9);
    }

    /**
     *
     * @param min
     * @param val
     * @param max
     * @return
     */
    public static double border(double min, double val, double max) {
        return Math.min(Math.min(max, val), max);
    }

    /**
     *
     * @param cdfI
     * @param index
     * @return
     */
    public static int getLengthIndex(int[] cdfI, int index) {
        int i = Arrays.binarySearch(cdfI, index);
        if (i < 0) {
            i = ~i;
        }
        return i;
    }

    /**
     *
     * @param <T>
     * @param values
     * @return
     */
    public static <T> int hashCode(T... values) {
        int hash = 7;
        for (T val : values) {
            hash = 17 * hash + val.hashCode();
        }
        return hash;
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <T> T headOrNull(Iterable<T> iterable) {
        for (T t : iterable) {
            return t;
        }
        return null;
    }

    /**
     *
     * @param <T>
     * @param values
     * @return
     */
    public static <T> int hashCode(int... values) {
        int hash = 7;
        for (int val : values) {
            hash = 17 * hash + ((Integer) val).hashCode();
        }
        return hash;
    }

    /**
     *
     * @param data
     * @return
     */
    public static int countOnes(long data) {
        int ones = 0;
        while (data != 0) {
            ones += data & 1;
            data >>>= 1;
        }
        return ones;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
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

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean arrayEquality(int[] a, int[] b) {
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

    public static boolean arrayEquality(int[][] a, int[][] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (!Utils.arrayEquality(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param <T>
     * @param ta
     * @param tb
     * @return
     */
    public static <T> boolean arrayEquality(Iterator<T> ta, Iterator<T> tb) {
        boolean na = ta.hasNext(), nb = tb.hasNext();
        while (na && nb) {
            if (!ta.next().equals(tb.next())) {
                return false;
            }
            na = ta.hasNext();
            nb = tb.hasNext();
        }
        return na == nb;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean arrayEquality(double[] a, double[] b) {
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

    /**
     *
     * @param inp
     * @return
     */
    public static String stringReverse(String inp) {
        StringBuilder sb = new StringBuilder();
        for (int i = inp.length() - 1; i >= 0; i--) {
            sb.append(inp.charAt(i));
        }
        return sb.toString();
    }

    /**
     *
     * @param <T>
     * @param a
     * @param b
     * @return
     */
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

    /**
     *
     * @param from
     * @param delta
     * @param to
     * @return
     */
    public static Iterable<Integer> sequence(int from, int delta, int to) {
        return new Iterable<Integer>() {
            private int from, delta, to;

            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int to;
                    private int delta;
                    private int value;

                    @Override
                    public boolean hasNext() {
                        return value < to;
                    }

                    @Override
                    public Integer next() {
                        int val = this.value;
                        this.value += delta;
                        return val;
                    }

                    public Iterator<Integer> setValues(int from, int delta, int to) {
                        this.delta = delta;
                        this.value = from;
                        this.to = to;
                        return this;
                    }

                    @Override
                    public void remove() {
                        //Ignore remove (this is an automatically generated sequence)
                    }
                }.setValues(from, delta, to);

            }

            private Iterable<Integer> setValues(int from, int delta, int to) {
                this.from = from;
                this.delta = delta;
                this.to = to;
                return this;
            }
        }.setValues(from, delta, to);
    }

    /**
     *
     * @param to
     * @return
     */
    public static Iterable<Integer> sequence(int to) {
        return sequence(0, 1, to);
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public static Iterable<Integer> sequence(int from, int to) {
        return sequence(from, 1, to);
    }

    /**
     *
     * @param offset
     * @param delta
     * @param modulo
     * @return
     */
    public static Iterable<Integer> sequenceModulo(int offset, int delta, int modulo) {
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

    /**
     *
     * @param offset
     * @param modulo
     * @return
     */
    public static Iterable<Integer> sequenceModulo(int offset, int modulo) {
        return sequenceModulo(offset, 1, modulo);
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }

    /**
     *
     * @param <T>
     * @param collection
     * @param predicate
     * @return
     */
    public static <T> boolean any(Iterable<T> collection, Generator<T, Boolean> predicate) {
        for (T t : collection) {
            if (predicate.generate(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param <T>
     * @param collection
     * @param predicate
     * @return
     */
    public static <T> boolean all(Iterable<T> collection, Generator<T, Boolean> predicate) {
        for (T t : collection) {
            if (!predicate.generate(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param <TFrom>
     * @param <TTo>
     * @param collection
     * @param generator
     * @return
     */
    public static <TFrom, TTo> HashMap<TFrom, TTo> generateMapping(Collection<TFrom> collection, Generator<TFrom, TTo> generator) {
        HashMap<TFrom, TTo> mapping = new HashMap<>();
        for (TFrom from : collection) {
            mapping.put(from, generator.generate(from));
        }
        return mapping;
    }

    /**
     *
     * @param <TFrom>
     * @param <TTo>
     * @param iterable
     * @param generator
     * @return
     */
    public static <TFrom, TTo> ArrayList<TTo> generateMappedArrayList(Iterable<TFrom> iterable, Generator<TFrom, TTo> generator) {
        ArrayList<TTo> result = new ArrayList<>();
        for (TFrom x : iterable) {
            result.add(generator.generate(x));
        }
        return result;
    }

    /**
     *
     * @param <T>
     * @param function
     * @param iterable
     * @return
     */
    public static <T> T fold(Generator<Tuple2<? extends T, ? extends T>, ? extends T> function, Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (it.hasNext()) {
            T temp = it.next();
            while (it.hasNext()) {
                temp = function.generate(new Tuple2<>(temp, it.next()));

            }
            return temp;
        } else {
            return null;
        }
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <T> HashMap<T, Integer> generateIndexHashMapper(Iterable<T> iterable) {
        return generateIndexMapper(iterable, new HashMap<T, Integer>());
    }

    /**
     *
     * @param <T>
     * @param iterable
     * @return
     */
    public static <T> HashBiMap<T, Integer> generateIndexHashBiMapper(Iterable<T> iterable) {
        HashBiMap<T, Integer> map = HashBiMap.create();
        return generateIndexMapper(iterable, map);
    }

    /**
     *
     * @param <T>
     * @param <TMap>
     * @param iterable
     * @param mapping
     * @return
     */
    public static <T, TMap extends Map<T, Integer>> TMap generateIndexMapper(Iterable<T> iterable, TMap mapping) {
        int index = 0;
        for (T t : iterable) {
            mapping.put(t, index++);
        }
        return mapping;
    }

    /**
     *
     * @param tmp
     * @return
     */
    public static int ceiling2Log(int tmp) {
        int d = 0;
        while (tmp != 0x00) {
            tmp >>= 1;
            d++;
        }
        return d;
    }

    /**
     *
     * @param original
     * @return
     */
    public static int next2Pow(int original) {
        original = fillTail(original);
        return original + 1;
    }

    /**
     *
     * @param original
     * @return
     */
    public static int base2Pow(int original) {
        return (fillTail(original) + 1) >> 1;
    }

    /**
     *
     * @param original
     * @return
     */
    public static int fillTail(int original) {
        original |= original >> 0x10;
        original |= original >> 0x08;
        original |= original >> 0x04;
        original |= original >> 0x02;
        original |= original >> 0x01;
        return original;
    }

    private Utils() {
    }

    int priority2Log(int tmp) {
        return ceiling2Log(tmp) - 1;
    }

    /**
     *
     * @param seed
     */
    public synchronized void setSeed(long seed) {
        StaticRandom.setSeed(seed);
    }

    /**
     *
     * @param bytes
     */
    public void nextBytes(byte[] bytes) {
        StaticRandom.nextBytes(bytes);
    }
}
