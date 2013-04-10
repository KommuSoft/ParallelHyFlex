package parallelhyflex.utils;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 */
public class ReversedDoubleComparator implements Comparator<Double> {

    private static final ReversedDoubleComparator singleInstance = new ReversedDoubleComparator();

    public static ReversedDoubleComparator getInstance() {
        return singleInstance;
    }

    private ReversedDoubleComparator() {
    }

    @Override
    public int compare(Double o1, Double o2) {
        return o2.compareTo(o1);
    }
}
