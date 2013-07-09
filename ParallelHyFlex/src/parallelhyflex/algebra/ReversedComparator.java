package parallelhyflex.algebra;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 */
public class ReversedComparator<T> implements Comparator<T> {

    private final Comparator<T> originalComparator;

    /**
     *
     * @param originalComparator
     */
    public ReversedComparator(Comparator<T> originalComparator) {
        this.originalComparator = originalComparator;
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(T o1, T o2) {
        return this.originalComparator.compare(o2, o1);
    }
}
