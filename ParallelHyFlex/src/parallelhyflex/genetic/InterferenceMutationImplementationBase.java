package parallelhyflex.genetic;

import parallelhyflex.interference.InterferenceStructure;

/**
 *
 * @author kommusoft
 */
public abstract class InterferenceMutationImplementationBase implements InterferenceMutationImplementation {

    @Override
    public void mutateLocal(InterferenceStructure<Integer> interference, int[] input, int[][] ranges) {
        this.mutateLocal(NullManipulationObserver.getInstance(), interference, input, ranges);
    }
    
}
