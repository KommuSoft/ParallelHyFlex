package parallelhyflex.genetic.mutation;

import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public interface MutationImplementation {

    /**
     *
     * @param input
     * @param ranges
     * @return
     */
    int[] mutate(int[] input, int[][] ranges);

    /**
     *
     * @param input
     * @param ranges
     */
    void mutateLocal(int[] input, int[][] ranges);

    /**
     *
     * @param observer
     * @param input
     * @param ranges
     */
    void mutateLocal(ManipulationObserver observer, int[] input, int[][] ranges);
}
