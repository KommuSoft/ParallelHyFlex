package parallelhyflex.experiencestorage;

import java.util.Objects;
import java.util.PriorityQueue;
import parallelhyflex.problemdependent.constraints.Constraint;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ReversedDoubleComparator;

/**
 *
 * @author kommusoft
 */
public class SetHypothesisItem<TSolution extends Solution<TSolution>, THypothesis extends Constraint<TSolution>> {

    private final THypothesis hypo;
    private final PriorityQueue<Double> bestEvaluations;
    private final int historySize;

    /**
     *
     * @param hypothesis
     */
    public SetHypothesisItem(THypothesis hypothesis) {
        this(hypothesis, 5);
    }

    /**
     *
     * @param hypothesis
     * @param historySize
     */
    public SetHypothesisItem(THypothesis hypothesis, int historySize) {
        this.bestEvaluations = new PriorityQueue<>(historySize, ReversedDoubleComparator.getInstance());
        this.hypo = hypothesis;
        this.historySize = historySize;
    }

    /**
     *
     * @param solution
     * @param evaluation
     */
    public void checkInstance(TSolution solution, double evaluation) {
        if (this.getHypothesis().isSatisfied(solution)) {
            if (this.getBestEvaluations().size() < this.getHistorySize() || this.getBestEvaluations().peek() > evaluation) {
                if (this.getBestEvaluations().size() > this.historySize) {
                    this.getBestEvaluations().poll();
                }
                getBestEvaluations().offer(evaluation);
            }
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SetHypothesisItem) {
            SetHypothesisItem shi = (SetHypothesisItem) obj;
            return shi.getHypothesis().equals(this.getHypothesis());
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.getHypothesis());
        return hash;
    }

    /**
     * @return the hypo
     */
    public THypothesis getHypothesis() {
        return hypo;
    }

    /**
     * @return the bestEvaluations
     */
    public PriorityQueue<Double> getBestEvaluations() {
        return bestEvaluations;
    }

    /**
     * @return the historySize
     */
    public int getHistorySize() {
        return historySize;
    }
}
