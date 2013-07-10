package parallelhyflex.hyperheuristics.learning.learningschemes;

import java.util.logging.Logger;
import parallelhyflex.algebra.probability.NormalizedProbabilityVector;

public class LinearLearningScheme extends LearningSchemeBase {

    private double lambda1;
    private double lambda2;

    public LinearLearningScheme() {
        this(1.0d, 1.0d);
    }

    public LinearLearningScheme(double lambda1, double lambda2) {
        this.setLambda1(lambda1);
        this.setLambda2(lambda2);
    }

    @Override
    protected void execute(NormalizedProbabilityVector probabilities, int index, double reward) {
        for (int i = 0; i < index; i++) {
            updateNotEqual(probabilities, i, reward);
        }
        updateEqual(probabilities, index, reward);
        int length = probabilities.getSize();
        for (int i = index + 1; i < length; i++) {
            updateNotEqual(probabilities, i, reward);
        }
    }

    /**
     * @return the lambda1
     */
    public double getLambda1() {
        return lambda1;
    }

    /**
     * @param lambda1 the lambda1 to set
     */
    public void setLambda1(double lambda1) {
        this.lambda1 = lambda1;
    }

    /**
     * @return the lambda2
     */
    public double getLambda2() {
        return lambda2;
    }

    /**
     * @param lambda2 the lambda2 to set
     */
    public void setLambda2(double lambda2) {
        this.lambda2 = lambda2;
    }

    private void updateNotEqual(NormalizedProbabilityVector probabilities, int index, double reward) {
        double arg1 = probabilities.getProbability(index);
        this.update(probabilities, index, -1.0d, reward, arg1, 1.0d / (probabilities.getSize() - 1.0d) - arg1);
    }

    private void update(NormalizedProbabilityVector probabilities, int index, double signum, double reward, double arg1, double arg2) {
        probabilities.addProbability(index,signum * (lambda1 * reward * arg1 - lambda2 * (1.0d - reward) * arg2));
    }

    private void updateEqual(NormalizedProbabilityVector probabilities, int index, double reward) {
        double arg2 = probabilities.getProbability(index);
        this.update(probabilities, index, 1.0d, reward, 1.0d - arg2, arg2);
    }
    private static final Logger LOG = Logger.getLogger(LinearLearningScheme.class.getName());
}
