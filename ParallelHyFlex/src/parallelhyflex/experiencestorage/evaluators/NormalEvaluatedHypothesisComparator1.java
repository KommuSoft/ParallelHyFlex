package parallelhyflex.experiencestorage.evaluators;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class NormalEvaluatedHypothesisComparator1 implements Comparator<NormalEvaluatedHypothesis> {

    private final static NormalEvaluatedHypothesisComparator1 instance = new NormalEvaluatedHypothesisComparator1();

    public static NormalEvaluatedHypothesisComparator1 getInstance() {
        return instance;
    }

    private NormalEvaluatedHypothesisComparator1() {
    }

    @Override
    public int compare(NormalEvaluatedHypothesis o1, NormalEvaluatedHypothesis o2) {
        return ((Double) o1.getEvaluation()).compareTo(o2.getEvaluation());
    }
    private static final Logger LOG = Logger.getLogger(NormalEvaluatedHypothesisComparator1.class.getName());
}
