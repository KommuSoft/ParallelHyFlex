package parallelhyflex.genetic.mutation;

import parallelhyflex.genetic.observer.NullManipulationObserver;

/**
 *
 * @author kommusoft
 */
public abstract class MutationImplementationBase implements MutationImplementation {

    /**
     *
     * @param input
     * @param ranges
     */
    @Override
    public void mutateLocal(int[] input, int[][] ranges) {
        this.mutateLocal(NullManipulationObserver.getInstance(), input, ranges);
    }
    
}
