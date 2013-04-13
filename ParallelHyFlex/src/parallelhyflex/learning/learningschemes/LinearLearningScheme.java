package parallelhyflex.learning.learningschemes;

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
    protected void execute(double[] probabilities, int index, double reward) {
        for (int i = 0; i < index; i++) {
            updateNotEqual(probabilities, i, reward);
        }
        updateEqual(probabilities, index, reward);
        for (int i = index + 1; i < probabilities.length; i++) {
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

    private void updateNotEqual(double[] probabilities, int index, double reward) {
        double arg1 = probabilities[index];
        this.update(probabilities, index, -1.0d, reward, arg1, 1.0d / (probabilities.length - 1.0d) - arg1);
    }

    private void update(double[] probabilities, int index, double signum, double reward, double arg1, double arg2) {
        probabilities[index] += signum * (lambda1 * reward * arg1 - lambda2 * (1.0d - reward) * arg2);
    }

    private void updateEqual(double[] probabilities, int index, double reward) {
        double arg2 = probabilities[index];
        this.update(probabilities, index, 1.0d, reward, 1.0d - arg2, arg2);
    }
}
