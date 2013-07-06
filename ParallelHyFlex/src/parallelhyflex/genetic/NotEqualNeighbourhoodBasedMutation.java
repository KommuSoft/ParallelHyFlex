package parallelhyflex.genetic;

import java.util.ArrayList;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class NotEqualNeighbourhoodBasedMutation implements MutationImplementation {

    private static final NotEqualNeighbourhoodBasedMutation instance = new NotEqualNeighbourhoodBasedMutation();

    public static NotEqualNeighbourhoodBasedMutation getInstance() {
        return instance;
    }
    private double DefaultPm = 0.5d;
    private int DefaultRepeat = 5;

    private NotEqualNeighbourhoodBasedMutation() {
    }

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
            index = Utils.StaticRandom.nextInt(n);
        }
        int val = ProbabilityUtils.randomElement(ranges[index]);
        for (int i = 0x00; i < index; i++) {
            if (input[i] == val) {
                affected.add(i);
                if (Utils.StaticRandom.nextDouble() < pm) {
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
                if (Utils.StaticRandom.nextDouble() < pm) {
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
}