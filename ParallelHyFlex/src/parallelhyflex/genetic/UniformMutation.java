package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

public class UniformMutation extends MutationImplementationBase {

    private static final UniformMutation instance = new UniformMutation();

    public static UniformMutation getInstance() {
        return instance;
    }

    private UniformMutation() {
    }

    @Override
    public int[] mutate(int[] input, int[][] ranges) {
        int n = input.length;
        int index = Utils.nextInt(n);
        int[] result = new int[n];
        System.arraycopy(input, 0x00, result, 0x00, index);
        System.arraycopy(input, index + 0x01, result, index + 0x01, n - index - 0x01);
        result[index] = ProbabilityUtils.randomElement(ranges[index]);
        return result;
    }

    /**
     *
     * @param observer
     * @param input
     * @param ranges
     */
    @Override
    public void mutateLocal(ManipulationObserver observer, int[] input, int[][] ranges) {
        int index = Utils.nextInt(input.length);
        int val = ProbabilityUtils.randomElement(ranges[index]);
        observer.modify(index, val);
        input[index] = val;
    }
}
