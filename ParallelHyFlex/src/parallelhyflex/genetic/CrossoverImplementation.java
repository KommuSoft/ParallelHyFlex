package parallelhyflex.genetic;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface CrossoverImplementation {

    int[] crossover(Collection<Integer> genes, int[]... parents);

    void crossoverLocal(Collection<Integer> genes, int[]... parents);

    void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, int[]... parents);

    int[] crossover(int[]... parents);

    void crossoverLocal(int[]... parents);

    void crossoverLocal(ManipulationObserver observer, int[]... parents);
}
