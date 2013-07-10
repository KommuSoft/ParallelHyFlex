package parallelhyflex.hyperheuristics.learning.learningschemes;

import parallelhyflex.algebra.probability.NormalizedProbabilityVector;
import parallelhyflex.algebra.tuples.Tuple3;


public abstract class LearningSchemeBase implements LearningScheme {

    @Override
    public void execute(Tuple3<NormalizedProbabilityVector, Integer, Double> parameter) {
        this.execute(parameter.getItem1(),parameter.getItem2(),parameter.getItem3());
    }

    /**
     *
     * @param probabilities
     * @param index
     * @param reward
     */
    protected abstract void execute(NormalizedProbabilityVector probabilities, int index, double reward);
    
}
