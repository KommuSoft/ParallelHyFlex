/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import java.util.Arrays;
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

    public Iterator<Integer> getLimitedModuleEnumerable(int modulo, int offset) {
        return new Iterator<Integer>() {
            private int modulo;
            private int value = 0;
            private int offset;

            @Override
            public boolean hasNext() {
                return value < modulo;
            }

            @Override
            public Integer next() {
                int val = this.value++;
                return (val + offset) % modulo;
            }

            public Iterator<Integer> setValues(int modulo, int offset) {
                this.modulo = modulo;
                this.offset = offset;
                return this;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove from a virtual list.");
            }
        }.setValues(modulo, offset);
    }
}
