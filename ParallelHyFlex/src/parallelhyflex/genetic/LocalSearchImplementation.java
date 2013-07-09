package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationGuiderObserver;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public interface LocalSearchImplementation {

    int[] localSearch(ManipulationGuider guider, int[] input, int[][] ranges);

    void localSearchLocal(ManipulationGuider guider, int[] input, int[][] ranges);

    void localSearchLocal(ManipulationGuider guider, ManipulationObserver observer, int[] input, int[][] ranges);

    void localSearchLocal(ManipulationGuiderObserver guiderObserver, int[] input, int[][] ranges);
}
