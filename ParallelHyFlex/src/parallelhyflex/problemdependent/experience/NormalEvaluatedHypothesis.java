/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problemdependent.experience;

import parallelhyflex.utils.StatisticsUtils;

public class NormalEvaluatedHypothesis implements EvaluatedHypothesis {

    private int nFalse = 0;
    private int nTrue = 0;
    private double meanFalse, meanTrue, m2False, m2True;

    public double getFalseMean() {
        return this.meanFalse;
    }

    public double getFalseVariance() {
        return this.m2False / this.nFalse;
    }

    public double getTrueMean() {
        return this.meanTrue;
    }

    public double getTrueVariance() {
        return this.m2True / this.nTrue;
    }

    int getNumberOfFalseEvaluations() {
        return this.nFalse;
    }

    int getNumberOfTrueEvaluations() {
        return this.nTrue;
    }

    @Override
    public int getNumberOfEvaluations() {
        return this.getNumberOfFalseEvaluations() + this.getNumberOfTrueEvaluations();
    }

    @Override
    public void evaluateTrue(double fitness) {
        this.nTrue++;
        double delta = fitness - this.meanTrue;
        this.meanTrue += delta / this.nTrue;
        this.m2True += delta * (fitness - this.meanTrue);
    }

    @Override
    public void evaluateFalse(double fitness) {
        this.nFalse++;
        double delta = fitness - this.meanFalse;
        this.meanFalse += delta / this.nFalse;
        this.m2False += delta * (fitness - this.meanFalse);
    }

    @Override
    public double getEvaluation() {
        if (this.getNumberOfFalseEvaluations() <= 1 || this.getNumberOfTrueEvaluations() <= 1) {
            return 0.0d;
        } else {
            double mx = this.meanTrue, my = this.meanFalse;
            double sx = this.getTrueVariance(), sy = this.getFalseVariance();
            return StatisticsUtils.normalCdf(this.meanTrue - this.meanFalse, sx * sx + sy * sy, 0.0d);
        }
    }
}
