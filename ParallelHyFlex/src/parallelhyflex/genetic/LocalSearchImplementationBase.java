package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationGuiderObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;

/**
 *
 * @author kommusoft
 */
public abstract class LocalSearchImplementationBase implements LocalSearchImplementation {

    /**
     *
     * @param guider
     * @param input
     * @param ranges
     */
    @Override
    public void localSearchLocal(ManipulationGuider guider, int[] input, int[][] ranges) {
        this.localSearchLocal(guider, NullManipulationObserver.getInstance(), input, ranges);
    }

    /**
     *
     * @param guiderObserver
     * @param input
     * @param ranges
     */
    @Override
    public void localSearchLocal(ManipulationGuiderObserver guiderObserver, int[] input, int[][] ranges) {
        this.localSearchLocal(guiderObserver, guiderObserver, input, ranges);
    }
}
