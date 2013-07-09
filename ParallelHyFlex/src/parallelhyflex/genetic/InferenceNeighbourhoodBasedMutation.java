package parallelhyflex.genetic;

import java.util.ArrayList;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class InferenceNeighbourhoodBasedMutation extends InterferenceMutationImplementationBase {

    private static final InferenceNeighbourhoodBasedMutation instance = new InferenceNeighbourhoodBasedMutation();

    public static InferenceNeighbourhoodBasedMutation getInstance() {
        return instance;
    }
    private double DefaultPm = 0.5d;
    private int DefaultRepeat = 5;

    private InferenceNeighbourhoodBasedMutation() {
    }

    @Override
    public int[] mutate(InterferenceStructure<Integer> interference, int[] input, int[][] ranges) {
        return this.mutate(interference, input, ranges, this.getDefaultPm(), this.getDefaultRepeat());
    }

    public int[] mutate(InterferenceStructure<Integer> interference, int[] input, int[][] ranges, double pm, int repeat) {
        int n = input.length;
        int[] sender = new int[n], receiver = new int[n], temp;
        ArrayList<Integer> affected = new ArrayList<>(Math.max((int) Math.sqrt(input.length), 0x03));
        mutationStep(interference, input, ranges, pm, sender, affected);
        for (int k = 0x01; k < repeat; k++) {
            mutationStep(interference, sender, ranges, pm, receiver, affected);
            temp = sender;
            sender = receiver;
            receiver = temp;
        }
        return sender;
    }

    private void mutationStep(InterferenceStructure<Integer> interference, int[] input, int[][] ranges, double pm, int[] result, ArrayList<Integer> affected) {
        int n = input.length, index;
        if (affected.size() > 0x00) {
            index = ProbabilityUtils.randomElement(affected);
            affected.clear();
        } else {
            index = Utils.nextInt(n);
        }
        int val = ProbabilityUtils.randomElement(ranges[index]);
        for (int i = 0x00; i < index; i++) {
            if (interference.interferes(index, i)) {
                affected.add(i);
                if (Utils.nextDouble() < pm) {
                    result[i] = ProbabilityUtils.randomElement(ranges[i]);
                } else {
                    result[i] = input[i];
                }
            } else {
                result[i] = input[i];
            }
        }
        result[index] = val;
        for (int i = index + 0x01; i < n; i++) {
            if (interference.interferes(index, i)) {
                affected.add(i);
                if (Utils.nextDouble() < pm) {
                    result[i] = ProbabilityUtils.randomElement(ranges[i]);
                } else {
                    result[i] = input[i];
                }
            } else {
                result[i] = input[i];
            }
        }
    }

    /**
     * @return the DefaultPm
     */
    public double getDefaultPm() {
        return DefaultPm;
    }

    /**
     * @param DefaultPm the DefaultPm to set
     */
    public void setDefaultPm(double DefaultPm) {
        this.DefaultPm = DefaultPm;
    }

    /**
     * @return the DefaultRepeat
     */
    public int getDefaultRepeat() {
        return DefaultRepeat;
    }

    /**
     * @param DefaultRepeat the DefaultRepeat to set
     */
    public void setDefaultRepeat(int DefaultRepeat) {
        this.DefaultRepeat = DefaultRepeat;
    }

    /**
     *
     * @param observer
     * @param interference
     * @param input
     * @param ranges
     */
    @Override
    public void mutateLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[] input, int[][] ranges) {
        this.mutateLocal(observer, interference, input, ranges, this.getDefaultPm(), this.getDefaultRepeat());
    }

    /**
     *
     * @param observer
     * @param interference
     * @param input
     * @param ranges
     * @param pm
     * @param repeat
     */
    public void mutateLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[] input, int[][] ranges, double pm, int repeat) {
        int n = input.length;
        ArrayList<Integer> affected = new ArrayList<>(Math.max((int) Math.sqrt(input.length), 0x03));
        for (int k = 0x00; k < repeat; k++) {
            mutationStepLocal(observer, interference, input, ranges, pm, affected);
        }
    }

    private void mutationStepLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[] input, int[][] ranges, double pm, ArrayList<Integer> affected) {
        int n = input.length, index;
        if (affected.size() > 0x00) {
            index = ProbabilityUtils.randomElement(affected);
            affected.clear();
        } else {
            index = Utils.nextInt(n);
        }
        int val;
        for (int i = 0x00; i < index; i++) {
            if (interference.interferes(index, i)) {
                affected.add(i);
                if (Utils.nextDouble() < pm) {
                    val = ProbabilityUtils.randomElement(ranges[i]);
                    observer.modify(i, val);
                    input[i] = val;
                }
            }
        }
        val = ProbabilityUtils.randomElement(ranges[index]);
        observer.modify(index, val);
        input[index] = val;
        for (int i = index + 0x01; i < n; i++) {
            if (interference.interferes(index, i)) {
                affected.add(i);
                if (Utils.nextDouble() < pm) {
                    val = ProbabilityUtils.randomElement(ranges[i]);
                    observer.modify(i, val);
                    input[i] = val;
                }
            }
        }
    }
}
