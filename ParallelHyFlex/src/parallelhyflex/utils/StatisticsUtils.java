/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author kommusoft
 */
public class StatisticsUtils {

    public static double mean(double[] vals) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += d;
        }
        return sum / vals.length;
    }

    public static double mean(Collection<Double> vals) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += d;
        }
        return sum / vals.size();
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

    public static double variation(Collection<Double> vals) {
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

    public static double variation(Collection<Double> vals, double mean) {
        double sum = 0.0d;
        for (double d : vals) {
            sum += (d - mean) * (d - mean);
        }
        return sum / vals.size();
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

    public static double min(Collection<Double> vals) {
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

    public static double max(Collection<Double> vals) {
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

    public static double pqEntropy(double p) {
        if (p < 1e-9 || p > 1 - 1e-9) {
            return 0.0d;
        } else {
            return ((p - 1) * Math.log(1 - p) - p * Math.log(p)) / Math.log(2);
        }
    }

    private StatisticsUtils() {
    }
}
