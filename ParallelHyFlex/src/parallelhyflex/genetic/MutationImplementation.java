package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
public interface MutationImplementation {

    int[] mutate(int[] input, int[][] ranges);

    void mutateLocal(int[] input, int[][] ranges);

    void mutateLocal(ManipulationObserver observer, int[] input, int[][] ranges);
}
