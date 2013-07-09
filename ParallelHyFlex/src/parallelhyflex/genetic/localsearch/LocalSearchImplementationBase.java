package parallelhyflex.genetic.localsearch;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationGuiderObserver;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;
import parallelhyflex.utils.IntegerUniqueRandomGenerator;

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

    /**
     *
     * @param guider
     * @param observer
     * @param input
     * @param ranges
     */
    @Override
    public void localSearchLocal(ManipulationGuider guider, ManipulationObserver observer, int[] input, int[][] ranges) {
        int n = input.length;
        IntegerUniqueRandomGenerator urg = new IntegerUniqueRandomGenerator(n);
        localSearchLocalInternal(guider, observer, ranges, urg, input);
    }

    protected abstract void localSearchLocalInternal(ManipulationGuider guider, ManipulationObserver observer, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] input);
}
