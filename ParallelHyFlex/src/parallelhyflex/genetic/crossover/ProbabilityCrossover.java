package parallelhyflex.genetic.crossover;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import parallelhyflex.algebra.collections.ConstantInfiniteList;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class ProbabilityCrossover extends CrossoverImplementationBase {

    private static final ProbabilityCrossover instance = new ProbabilityCrossover();

    /**
     *
     * @return
     */
    public static ProbabilityCrossover getInstance() {
        return instance;
    }

    private ProbabilityCrossover() {
    }

    /**
     *
     * @param genes
     * @param parents
     * @return
     */
    public int[] crossover(Collection<Integer> genes, List<Double> probabilities, int[]... parents) {
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
            int l = ProbabilityUtils.randomBoundedIndexFromPDF(probabilities, m);
            int ie = Math.min(n, i + gi.next());
            for (; i < ie; i++) {
                values[i] = parents[l][i];
            }
        }
        return values;
    }

    /**
     *
     * @param observer
     * @param genes
     * @param probabilities 
     * @param parents
     */
    public void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, List<Double> probabilities, int[]... parents) {
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
                int l = ProbabilityUtils.randomBoundedIndexFromPDF(probabilities, m);
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

    @Override
    public int[] crossover(Collection<Integer> genes, int[]... parents) {
        return this.crossover(genes, new ConstantInfiniteList<>(1.0d / parents.length), parents);
    }

    @Override
    public void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(observer, genes, new ConstantInfiniteList<>(1.0d / parents.length), parents);
    }
}
