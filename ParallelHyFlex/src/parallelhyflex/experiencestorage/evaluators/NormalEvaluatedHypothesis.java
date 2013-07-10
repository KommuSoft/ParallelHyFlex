/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.experiencestorage.evaluators;

import java.util.logging.Logger;
import parallelhyflex.problemdependent.constraints.Constraint;
import parallelhyflex.problemdependent.experience.EvaluatedHypothesis;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.StatisticsUtils;

/**
 *
 * @author kommusoft
 */
public class NormalEvaluatedHypothesis<TSolution extends Solution<TSolution>, THypothesis extends Constraint<TSolution>> implements EvaluatedHypothesis<TSolution, THypothesis> {

    private static final Logger LOG = Logger.getLogger(NormalEvaluatedHypothesis.class.getName());
    private final THypothesis hypothesis;
    private int nFalse = 0;
    private int nTrue = 0;
    private double meanFalse, meanTrue, m2False, m2True;

    public NormalEvaluatedHypothesis(THypothesis hypothesis) {
        this.hypothesis = hypothesis;
    }

    /**
     *
     * @return
     */
    public double getFalseMean() {
        return this.meanFalse;
    }

    /**
     *
     * @return
     */
    public double getFalseVariance() {
        return this.m2False / this.nFalse;
    }

    /**
     *
     * @return
     */
    public double getTrueMean() {
        return this.meanTrue;
    }

    /**
     *
     * @return
     */
    public double getTrueVariance() {
        return this.m2True / this.nTrue;
    }

    int getNumberOfFalseEvaluations() {
        return this.nFalse;
    }

    int getNumberOfTrueEvaluations() {
        return this.nTrue;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfEvaluations() {
        return this.getNumberOfFalseEvaluations() + this.getNumberOfTrueEvaluations();
    }

    /**
     *
     * @param fitness
     */
    @Override
    public void evaluateTrue(double fitness) {
        this.nTrue++;
        double delta = fitness - this.meanTrue;
        this.meanTrue += delta / this.nTrue;
        this.m2True += delta * (fitness - this.meanTrue);
    }

    /**
     *
     * @param fitness
     */
    @Override
    public void evaluateFalse(double fitness) {
        this.nFalse++;
        double delta = fitness - this.meanFalse;
        this.meanFalse += delta / this.nFalse;
        this.m2False += delta * (fitness - this.meanFalse);
    }

    /**
     *
     * @return
     */
    @Override
    public double getEvaluation() {
        if (this.getNumberOfFalseEvaluations() <= 1 || this.getNumberOfTrueEvaluations() <= 1) {
            return 0.5d;
        } else {
            double mx = this.meanTrue, my = this.meanFalse;
            double sx = this.getTrueVariance(), sy = this.getFalseVariance();
            return StatisticsUtils.normalCdf(this.meanTrue - this.meanFalse, sx * sx + sy * sy, 0.0d);
        }
    }

    @Override
    public THypothesis getHypothesis() {
        return this.hypothesis;
    }

    @Override
    public void evaluate(TSolution solution, double fitness) {
        if (this.hypothesis.isSatisfied(solution)) {
            this.evaluateTrue(fitness);
        } else {
            this.evaluateFalse(fitness);
        }
    }
}
