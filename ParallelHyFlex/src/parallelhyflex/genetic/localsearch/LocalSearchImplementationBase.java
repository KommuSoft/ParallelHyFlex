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
     * @param guider
     * @param input
     * @param ranges
     * @return
     */
    @Override
    public int[] localSearch(ManipulationGuider guider, int[] input, int[][] ranges) {
        int n = input.length;
        int[] cache = new int[n], cache2 = new int[n], tmp;
        System.arraycopy(input, 0x00, cache, 0x00, n);
        IntegerUniqueRandomGenerator urg = new IntegerUniqueRandomGenerator(input.length);
        boolean modified;
        do {
            modified = localSearchIteration(guider, ranges, urg, cache, cache2);
            urg.reset();
            tmp = cache;
            cache = cache2;
            cache2 = tmp;
        } while (modified);
        return cache;
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
        int[] cache = new int[n], cache2 = new int[n], tmp;
        System.arraycopy(input, 0x00, cache, 0x00, n);
        IntegerUniqueRandomGenerator urg = new IntegerUniqueRandomGenerator(input.length);
        boolean modified;
        do {
            modified = localSearchLocalIteration(guider, observer, ranges, urg, input);
            urg.reset();
            tmp = cache;
            cache = cache2;
            cache2 = tmp;
        } while (modified);
    }

    protected abstract boolean localSearchIteration(ManipulationGuider guider, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] cache, int[] cache2);

    protected abstract boolean localSearchLocalIteration(ManipulationGuider guider, ManipulationObserver observer, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] input);
}
