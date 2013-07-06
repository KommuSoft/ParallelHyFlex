package parallelhyflex.genetic;

import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class InterferenceCrossover implements InterferenceCrossoverImplementation {

    private static final InterferenceCrossover instance = new InterferenceCrossover();

    public static InterferenceCrossover getInstance() {
        return instance;
    }

    private InterferenceCrossover() {
    }

    @Override
    public int[] crossover(InterferenceStructure<Integer> interference, int[]... parents) {
        int m = parents.length;
        if (m <= 0x00) {
            return null;
        }
        int keyparent = Utils.nextInt(m);
        int n = parents[0].length;
        for (int i = 1; i < m; i++) {
            n = Math.min(n, parents[i].length);
        }
        int keyindex = Utils.nextInt(n);
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            if (interference.interferes(keyindex, i)) {
                values[i] = parents[keyparent][i];
            } else {
                values[i] = parents[Utils.ignoreRandomIndex(m, keyparent)][i];
            }
        }
        return values;
    }
}
