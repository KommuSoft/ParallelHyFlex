package parallelhyflex.experiencestorage;

import java.util.Comparator;
import parallelhyflex.problemdependent.Constraint;
import parallelhyflex.problemdependent.Solution;

/**
 *
 * @author kommusoft
 */
public class SetHypothesisItemComparator<TSolution extends Solution<TSolution>, THypothesis extends Constraint<TSolution>> implements Comparator<SetHypothesisItem<TSolution,THypothesis>> {

    @Override
    public int compare(SetHypothesisItem<TSolution, THypothesis> o1, SetHypothesisItem<TSolution, THypothesis> o2) {
        
    }
    
}
