package parallelhyflex.genetic.constraint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.algebra.tuples.Tuple2;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

public class KeyValueConstraintImplementationBase extends ConstraintImplementationBase<Tuple2<Integer, Integer>> {

    private static final KeyValueConstraintImplementationBase instance = new KeyValueConstraintImplementationBase();

    public static KeyValueConstraintImplementationBase getInstance() {
        return instance;
    }

    private KeyValueConstraintImplementationBase() {
    }

    @Override
    public void enforceTrue(ManipulationObserver observer, Tuple2<Integer, Integer> constraint, int[] solution, int[][] ranges) {
        int index = constraint.getItem1();
        int value = constraint.getItem2();
        observer.modify(index, value);
        solution[constraint.getItem1()] = constraint.getItem2();
    }

    @Override
    public void enforceFalse(ManipulationObserver observer, Tuple2<Integer, Integer> constraint, int[] solution, int[][] ranges) {
        int index = constraint.getItem1();
        int value = constraint.getItem2();
        observer.modify(index, value);
        int newvalue;
        do {
            newvalue = ProbabilityUtils.randomElement(ranges[index]);
        } while (newvalue != value);
        observer.modify(index, value);
        solution[constraint.getItem1()] = newvalue;
    }

    @Override
    public boolean isSatisfied(Tuple2<Integer, Integer> constraint, int[] solution, int[][] ranges) {
        return (solution[constraint.getItem1()] == constraint.getItem2());
    }

    @Override
    public void write(Tuple2<Integer, Integer> constraint, DataOutputStream dos) throws IOException {
        dos.writeInt(constraint.getItem1());
        dos.writeInt(constraint.getItem2());
    }

    @Override
    public Tuple2<Integer, Integer> read(DataInputStream dis) throws IOException {
        return new Tuple2<>(dis.readInt(), dis.readInt());
    }

    @Override
    public Tuple2<Integer, Integer> generate(int[] solution, int[][] ranges) throws ConstraintRepresentationException {
        int m = 0x00;
        for (int i = 0x00; i < ranges.length; i++) {
            if (ranges[i].length > 0x01) {
                m++;
            }
        }
        int k = Utils.nextInt(m);
        for (int i = 0x00; i < ranges.length; i++) {
            if (ranges[i].length > 0x01) {
                k--;
                if (k < 0x00) {
                    return new Tuple2<>(i, solution[i]);
                }
            }
        }
        throw new ConstraintRepresentationException();
    }
}
