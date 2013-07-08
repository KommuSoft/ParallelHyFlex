package parallelhyflex.genetic;

import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

public class UniformMutation implements MutationImplementation {

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

    @Override
    public void mutateLocal(int[] input, int[][] ranges) {
        int index = Utils.nextInt(input.length);
        input[index] = ProbabilityUtils.randomElement(ranges[index]);
    }
}
