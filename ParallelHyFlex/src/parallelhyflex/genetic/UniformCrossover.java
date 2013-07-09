package parallelhyflex.genetic;

import java.util.Collection;
import java.util.Iterator;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class UniformCrossover extends CrossoverImplementationBase {

    private static final UniformCrossover instance = new UniformCrossover();

    public static UniformCrossover getInstance() {
        return instance;
    }

    private UniformCrossover() {
    }

    @Override
    public int[] crossover(Collection<Integer> genes, int[]... parents) {
        int m = parents.length;
        if (m <= 0x00) {
            return null;
        }
        int n = parents[0].length;
        for (int i = 0x01; i < m; i++) {
            n = Math.min(n, parents[i].length);
        }
        int[] values = new int[n];
        Iterator<Integer> gi = genes.iterator();
        for (int i = 0x00; i < n && gi.hasNext();) {
            int l = Utils.nextInt(m);
            int ie = Math.min(n, i + gi.next());
            for (; i < ie; i++) {
                values[i] = parents[l][i];
            }
        }
        return values;
    }

    @Override
    public void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, int[]... parents) {
        int m = parents.length;
        if (m > 0x00) {
            int n = parents[0].length;
            for (int i = 0x01; i < m; i++) {
                n = Math.min(n, parents[i].length);
            }
            int[] values = parents[0x00];
            Iterator<Integer> gi = genes.iterator();
            for (int i = 0x00; i < n && gi.hasNext();) {
                int ie = Math.min(n, i + gi.next());
                int l = Utils.nextInt(m);
                if (l != 0x00) {
                    for (; i < ie; i++) {
                        observer.modify(i, parents[l][i]);
                        values[i] = parents[l][i];
                    }
                } else {
                    i = ie;
                }
            }
        }
    }
}
