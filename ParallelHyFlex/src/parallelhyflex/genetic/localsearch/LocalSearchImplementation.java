package parallelhyflex.genetic.localsearch;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationGuiderObserver;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public interface LocalSearchImplementation {

    /**
     *
     * @param guider
     * @param input
     * @param ranges
     */
    void localSearchLocal(ManipulationGuider guider, int[] input, int[][] ranges);

    /**
     *
     * @param guider
     * @param observer
     * @param input
     * @param ranges
     */
    void localSearchLocal(ManipulationGuider guider, ManipulationObserver observer, int[] input, int[][] ranges);

    /**
     *
     * @param guiderObserver
     * @param input
     * @param ranges
     */
    void localSearchLocal(ManipulationGuiderObserver guiderObserver, int[] input, int[][] ranges);
}
