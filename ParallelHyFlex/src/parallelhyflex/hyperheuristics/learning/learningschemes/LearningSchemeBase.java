package parallelhyflex.hyperheuristics.learning.learningschemes;

import parallelhyflex.algebra.tuples.Tuple3;


/**
 *
 * @author kommusoft
 */
public abstract class LearningSchemeBase implements LearningScheme {

    /**
     *
     * @param parameter
     */
    @Override
    public void execute(Tuple3<double[], Integer, Double> parameter) {
        this.execute(parameter.getItem1(),parameter.getItem2(),parameter.getItem3());
    }

    /**
     *
     * @param probabilities
     * @param index
     * @param reward
     */
    protected abstract void execute(double[] probabilities, int index, double reward);
    
}
