package parallelhyflex.genetic;

import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class InterferenceCrossover {

    private static final InterferenceCrossover instance = new InterferenceCrossover();

    public static InterferenceCrossover getInstance() {
        return instance;
    }

    private InterferenceCrossover() {
    }

    public int[] crossover(InterferenceStructure<Integer> interference, int[]... parents) {
        int m = parents.length;
        if (m <= 0x00) {
            return null;
        }
        int keyparent = Utils.StaticRandom.nextInt(m);
        int n = parents[0].length;
        for (int i = 1; i < m; i++) {
            n = Math.min(n, parents[i].length);
        }
        int keyindex = Utils.StaticRandom.nextInt(n);
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = parents[Utils.StaticRandom.nextInt(m)][i];
        }
        return values;
    }
}
