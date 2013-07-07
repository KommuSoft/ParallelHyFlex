package parallelhyflex.genetic;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface MutationImplementation {

    int[] mutate(int[] input, int[][] ranges);

    void mutateLocal(int[] input, int[][] ranges);

    void mutateLocal(Collection<Integer> modifiedIndices, int[] input, int[][] ranges);
}
