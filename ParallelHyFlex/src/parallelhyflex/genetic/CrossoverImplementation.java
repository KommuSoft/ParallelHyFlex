package parallelhyflex.genetic;

import java.util.Collection;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public interface CrossoverImplementation {

    int[] crossover(Collection<Integer> genes, int[]... parents);

    int[] crossover(int[]... parents);

    int[] crossover(int genes, int[]... parents);

    void crossoverLocal(Collection<Integer> genes, int[]... parents);

    void crossoverLocal(int genes, int[]... parents);

    void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, int[]... parents);

    void crossoverLocal(ManipulationObserver observer, int genes, int[]... parents);

    void crossoverLocal(int[]... parents);

    void crossoverLocal(ManipulationObserver observer, int[]... parents);
}
