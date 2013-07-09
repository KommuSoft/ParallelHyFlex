package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationObserver;
import java.util.ArrayList;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class NotEqualNeighbourhoodBasedMutation extends MutationImplementationBase {

    private static final NotEqualNeighbourhoodBasedMutation instance = new NotEqualNeighbourhoodBasedMutation();

    public static NotEqualNeighbourhoodBasedMutation getInstance() {
        return instance;
    }
    private double DefaultPm = 0.5d;
    private int DefaultRepeat = 5;

    private NotEqualNeighbourhoodBasedMutation() {
    }

    @Override
    public int[] mutate(int[] input, int[][] ranges) {
        return this.mutate(input, ranges, this.getDefaultPm(), this.getDefaultRepeat());
    }

    public int[] mutate(int[] input, int[][] ranges, double pm, int repeat) {
        int n = input.length;
        int[] sender = new int[n], receiver = new int[n], temp;
        ArrayList<Integer> affected = new ArrayList<>();
        mutationStep(input, ranges, pm, sender, affected);
        for (int k = 0x01; k < repeat; k++) {
            mutationStep(sender, ranges, pm, receiver, affected);
            temp = sender;
            sender = receiver;
            receiver = temp;
        }
        return sender;
    }

    private void mutationStep(int[] input, int[][] ranges, double pm, int[] result, ArrayList<Integer> affected) {
        int n = input.length, index;
        if (affected.size() > 0x00) {
            index = ProbabilityUtils.randomElement(affected);
            affected.clear();
        } else {
            index = Utils.nextInt(n);
        }
        int val = ProbabilityUtils.randomElement(ranges[index]);
        for (int i = 0x00; i < index; i++) {
            if (input[i] == val) {
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
            if (input[i] == val) {
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

    private void mutationStepLocal(ManipulationObserver observer, int[] input, int[][] ranges, double pm, ArrayList<Integer> affected) {
        int n = input.length, index;
        if (affected.size() > 0x00) {
            index = ProbabilityUtils.randomElement(affected);
            affected.clear();
        } else {
            index = Utils.nextInt(n);
        }
        int val = ProbabilityUtils.randomElement(ranges[index]), val2;
        for (int i = 0x00; i < index; i++) {
            if (input[i] == val) {
                affected.add(i);
                if (Utils.nextDouble() < pm) {
                    val2 = ProbabilityUtils.randomElement(ranges[i]);
                    observer.modify(i, val2);
                    input[i] = val2;
                }
            }
        }
        observer.modify(index, val);
        input[index] = val;
        for (int i = index + 0x01; i < n; i++) {
            if (input[i] == val) {
                affected.add(i);
                if (Utils.nextDouble() < pm) {
                    val2 = ProbabilityUtils.randomElement(ranges[i]);
                    observer.modify(i, val2);
                    input[i] = val2;
                }
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
     * @param input
     * @param ranges
     */
    @Override
    public void mutateLocal(ManipulationObserver observer, int[] input, int[][] ranges) {
        this.mutateLocal(observer, input, ranges, this.getDefaultPm(), this.getDefaultRepeat());
    }

    private void mutateLocal(ManipulationObserver observer, int[] input, int[][] ranges, double pm, int repeat) {
        int n = input.length;
        ArrayList<Integer> affected = new ArrayList<>();
        for (int k = 0x00; k < repeat; k++) {
            mutationStepLocal(observer, input, ranges, pm, affected);
        }
    }
}
