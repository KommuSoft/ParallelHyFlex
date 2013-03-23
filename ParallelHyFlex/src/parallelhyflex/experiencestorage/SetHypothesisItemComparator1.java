package parallelhyflex.experiencestorage;

import java.util.Comparator;
import java.util.PriorityQueue;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public class SetHypothesisItemComparator1 implements Comparator<SetHypothesisItem> {

    private static final SetHypothesisItemComparator1 staticInstance = new SetHypothesisItemComparator1();

    public static SetHypothesisItemComparator1 getInstance() {
        return staticInstance;
    }

    @Override
    public int compare(SetHypothesisItem o1, SetHypothesisItem o2) {
        if (!o1.equals(o2)) {
            PriorityQueue<Double> o1pq = o1.getBestEvaluations();
            PriorityQueue<Double> o2pq = o2.getBestEvaluations();
            return ((Double) StatisticsUtils.min(o1pq)).compareTo(StatisticsUtils.min(o2pq));
        } else {
            return 0x00;
        }
    }
}
