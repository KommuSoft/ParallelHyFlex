package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.interference.NoInterferenceStructure;

/**
 *
 * @author kommusoft
 */
public abstract class InterferenceMutationImplementationBase extends MutationImplementationBase implements InterferenceMutationImplementation {

    /**
     *
     * @param interference
     * @param input
     * @param ranges
     */
    @Override
    public void mutateLocal(InterferenceStructure<Integer> interference, int[] input, int[][] ranges) {
        this.mutateLocal(NullManipulationObserver.getInstance(), interference, input, ranges);
    }

    /**
     *
     * @param input
     * @param ranges
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public int[] mutate(int[] input, int[][] ranges) {
        return this.mutate(NoInterferenceStructure.getInstance(), input, ranges);
    }

    /**
     *
     * @param observer
     * @param input
     * @param ranges
     */
    @Override
    @SuppressWarnings("unchecked")
    public void mutateLocal(ManipulationObserver observer, int[] input, int[][] ranges) {
        this.mutateLocal(observer, NoInterferenceStructure.getInstance(), input, ranges);
    }

    /**
     *
     * @param interference
     * @param observer
     * @param input
     * @param ranges
     */
    @Override
    public void mutateLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, int[] input, int[][] ranges) {
        this.mutateLocal(observer, interference, input, ranges);
    }
}
