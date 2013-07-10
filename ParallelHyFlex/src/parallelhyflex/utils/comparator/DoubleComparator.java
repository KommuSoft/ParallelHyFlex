package parallelhyflex.utils.comparator;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class DoubleComparator implements Comparator<Double> {

    private static final DoubleComparator singleInstance = new DoubleComparator();
    private static final Logger LOG = Logger.getLogger(DoubleComparator.class.getName());

    /**
     *
     * @return
     */
    public static DoubleComparator getInstance() {
        return singleInstance;
    }

    private DoubleComparator() {
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Double o1, Double o2) {
        return (int) Math.signum(o1 - o2);
    }
}
