package parallelhyflex.genetic;

import parallelhyflex.interference.InterferenceStructure;

/**
 *
 * @author kommusoft
 */
interface InterferenceMutationImplementation extends MutationImplementation {

    int[] mutate(InterferenceStructure<Integer> interference, int[] input, int[][] ranges);

    void mutateLocal(InterferenceStructure<Integer> interference, int[] input, int[][] ranges);

    void mutateLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[] input, int[][] ranges);

    void mutateLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, int[] input, int[][] ranges);
}