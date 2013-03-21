/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kommusoft
 */
public class Utils {

    private Utils() {
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

    public static <T> void randomPermutate(T[] vals) {
        T tmp;
        int ind;
        for (int i = 0, j = vals.length; j > 1; i++, j--) {
            ind = i + Utils.StaticRandom.nextInt(j);
            tmp = vals[i];
            vals[i] = vals[ind];
            vals[ind] = tmp;
        }
    }

    public static int countOnes(long data) {
        int ones = 0;
        while (data != 0) {
            ones += data & 1;
            data >>= 1;
        }
        return ones;
    }

    public static double mean(double[] vals) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += d;
        }
        return sum / vals.length;
    }

    public static double mean(int[] vals) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += d;
        }
        return sum / vals.length;
    }

    public static double variation(double[] vals) {
        double mean = mean(vals);
        return variation(vals, mean);
    }

    public static double variation(int[] vals) {
        double mean = mean(vals);
        return variation(vals, mean);
    }

    public static double variation(double[] vals, double mean) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += (d - mean) * (d - mean);
        }
        return sum / vals.length;
    }

    public static double variation(int[] vals, double mean) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += (d - mean) * (d - mean);
        }
        return sum / vals.length;
    }

    public static double min(double[] vals) {
        double min = Double.POSITIVE_INFINITY;
        for (double d : vals) {
            if (d < min) {
                min = d;
            }
        }
        return min;
    }

    public static double min(int[] vals) {
        double min = Double.POSITIVE_INFINITY;
        for (double d : vals) {
            if (d < min) {
                min = d;
            }
        }
        return min;
    }

    public static double max(double[] vals) {
        double max = Double.NEGATIVE_INFINITY;
        for (double d : vals) {
            if (d > max) {
                max = d;
            }
        }
        return max;
    }

    public static double max(int[] vals) {
        double max = Double.NEGATIVE_INFINITY;
        for (double d : vals) {
            if (d > max) {
                max = d;
            }
        }
        return max;
    }

    public static double entropy(int[] vals) {
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int val : vals) {
            if (!frequency.containsKey(val)) {
                frequency.put(val, 1);
            } else {
                frequency.put(val, frequency.get(val) + 1);
            }
        }
        double sum = 0.0;
        for (int freq : frequency.values()) {
            double p = (double) freq / vals.length;
            sum -= p * Math.log(p);
        }
        return sum / Math.log(2.0d);
    }
    
    public static double pqEntropy (double p) {
        if(p < 1e-9 || p > 1-1e-9) {
            return 0.0d;
        }
        else {
            return ((p-1)*Math.log(1-p)-p*Math.log(p))/Math.log(2);
        }
    }

    public static <T> T randomElement(List<T> list) {
        return list.get(StaticRandom.nextInt(list.size()));
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
