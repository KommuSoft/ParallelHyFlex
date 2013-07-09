package parallelhyflex.genetic.crossover;

import java.util.Collection;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public interface CrossoverImplementation {

    /**
     *
     * @param genes
     * @param parents
     * @return
     */
    int[] crossover(Collection<Integer> genes, int[]... parents);

    /**
     *
     * @param parents
     * @return
     */
    int[] crossover(int[]... parents);

    /**
     *
     * @param genes
     * @param parents
     * @return
     */
    int[] crossover(int genes, int[]... parents);

    /**
     *
     * @param genes
     * @param parents
     */
    void crossoverLocal(Collection<Integer> genes, int[]... parents);

    /**
     *
     * @param genes
     * @param parents
     */
    void crossoverLocal(int genes, int[]... parents);

    /**
     *
     * @param observer
     * @param genes
     * @param parents
     */
    void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, int[]... parents);

    /**
     *
     * @param observer
     * @param genes
     * @param parents
     */
    void crossoverLocal(ManipulationObserver observer, int genes, int[]... parents);

    /**
     *
     * @param parents
     */
    void crossoverLocal(int[]... parents);

    /**
     *
     * @param observer
     * @param parents
     */
    void crossoverLocal(ManipulationObserver observer, int[]... parents);
}
