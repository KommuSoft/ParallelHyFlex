package parallelhyflex.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author kommusoft
 */
public class ProbabilityUtils {

    private ProbabilityUtils() {
    }

    public static <T> T randomElement(List<T> list) {
        return list.get(Utils.StaticRandom.nextInt(list.size()));
    }

    /**
     *
     * @param D
     * @return an Integer in the interval [0,D[
     */
    public static int integerFromBenfordDistribution(int D) {
        return (int) Math.floor(Math.pow(D + 1, Utils.StaticRandom.nextDouble())) - 1;
    }

    /**
     *
     * @param D
     * @return an Integer in the Interval [0,D[
     */
    public static int integerFromUniformDistribution(int D) {
        return (int) Math.floor(D * Utils.StaticRandom.nextDouble());
    }

    public static void unnormalizedWeightsToCDF(double[] weights) {
        double suminv = 0.0d;
        double min = 0.0d;
        for (double w : weights) {
            min = Math.min(w, min);
            suminv += w;
        }
        suminv -= min * weights.length;
        suminv = 1 / suminv;
        double c = 0.0d;
        for (int i = 0; i < weights.length; i++) {
            c += (weights[i] - min) * suminv;
            weights[i] = c;
        }
    }

    public static int getRandomIndexFromCDF(double[] cdf, Collection<Integer> without) {
        int index;
        do {
            index = getRandomIndexFromCDF(cdf);
        } while (without.contains(index));
        return index;
    }

    public static int getRandomIndexFromCDF(double[] cdf) {
        double rand = Utils.StaticRandom.nextDouble();
        int index = Arrays.binarySearch(cdf, rand);
        if (index < 0) {
            index = ~index;
        }
        return index;
    }
}
