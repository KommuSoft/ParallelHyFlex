package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public interface LocalSearchImplementation {

    int[] localSearch(int[] input, int[][] ranges);

    void localSearchLocal(int[] input, int[][] ranges);

    void localSearchLocal(ManipulationObserver observer, int[] input, int[][] ranges);
}
