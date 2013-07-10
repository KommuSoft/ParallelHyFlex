package parallelhyflex.utils.comparator;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ReversedDoubleComparator implements Comparator<Double> {

    private static final ReversedDoubleComparator singleInstance = new ReversedDoubleComparator();
    private static final Logger LOG = Logger.getLogger(ReversedDoubleComparator.class.getName());

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
        return (int) Math.signum(o2 - o1);
    }
}
