package parallelhyflex.experiencestorage;

import java.util.Comparator;
import java.util.PriorityQueue;
import parallelhyflex.problemdependent.Constraint;
import parallelhyflex.problemdependent.Solution;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public class SetHypothesisItemComparator1<TSolution extends Solution<TSolution>, THypothesis extends Constraint<TSolution>> implements Comparator<SetHypothesisItem<TSolution,THypothesis>> {

    @Override
    public int compare(SetHypothesisItem<TSolution, THypothesis> o1, SetHypothesisItem<TSolution, THypothesis> o2) {
        PriorityQueue<Double> o1pq = o1.getBestEvaluations();
        PriorityQueue<Double> o2pq = o2.getBestEvaluations();
        return ((Double) StatisticsUtils.min(o1pq)).compareTo(StatisticsUtils.min(o2pq));
    }
    
}
