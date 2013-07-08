package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
public abstract class MutationImplementationBase implements MutationImplementation {

    @Override
    public void mutateLocal(int[] input, int[][] ranges) {
        this.mutateLocal(NullManipulationObserver.getInstance(), input, ranges);
    }
    
}
