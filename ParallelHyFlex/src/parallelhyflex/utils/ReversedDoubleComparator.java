package parallelhyflex.utils;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 */
public class ReversedDoubleComparator implements Comparator<Double> {

    private static final ReversedDoubleComparator singleInstance = new ReversedDoubleComparator();

    /**
     *
     * @return
     */
    public static ReversedDoubleComparator getInstance() {
        return singleInstance;
    }

    private ReversedDoubleComparator() {
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Double o1, Double o2) {
        return o2.compareTo(o1);
    }
}
