package parallelhyflex.genetic;

import java.util.Collection;
import java.util.Iterator;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class InterferenceCrossover extends InterferenceCrossoverImplementationBase {

    private static final InterferenceCrossover instance = new InterferenceCrossover();

    public static InterferenceCrossover getInstance() {
        return instance;
    }

    private InterferenceCrossover() {
    }

    @Override
    public int[] crossover(InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents) {
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
        Iterator<Integer> gi = genes.iterator();
        for (int i = 0x00; i < n && gi.hasNext();) {
            int ie = Math.min(n, i + gi.next());
            int other = Utils.ignoreRandomIndex(m, keyparent);
            for (; i < ie; i++) {
                if (interference.interferes(keyindex, i)) {
                    values[i] = parents[keyparent][i];
                } else {
                    values[i] = parents[other][i];
                }
            }
        }
        return values;
    }

    /**
     *
     * @param interference
     * @param observer
     * @param genes
     * @param parents
     */
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, Collection<Integer> genes, int[]... parents) {
        int m = parents.length;
        if (m > 0x00) {
            int keyparent = Utils.nextInt(m);
            int n = parents[0].length;
            for (int i = 1; i < m; i++) {
                n = Math.min(n, parents[i].length);
            }
            int keyindex = Utils.nextInt(n);
            int[] values = parents[0x00];
            Iterator<Integer> gi = genes.iterator();
            for (int i = 0; i < n && gi.hasNext();) {
                int ie = Math.min(n, i + gi.next());
                int other = Utils.ignoreRandomIndex(m, keyparent);
                for (; i < ie; i++) {
                    if (interference.interferes(keyindex, i)) {
                        if (keyparent != 0x00) {
                            observer.modify(i, parents[keyparent][i]);
                            values[i] = parents[keyparent][i];
                        }
                    } else if (other != 0x00) {
                        observer.modify(i, parents[other][i]);
                        values[i] = parents[other][i];
                    }
                }
            }
        }
    }
}
