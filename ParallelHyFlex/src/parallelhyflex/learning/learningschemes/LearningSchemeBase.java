package parallelhyflex.learning.learningschemes;

import parallelhyflex.algebra.tuples.Tuple3;


public abstract class LearningSchemeBase implements LearningScheme {

    @Override
    public void execute(Tuple3<double[], Integer, Double> parameter) {
        this.execute(parameter.getItem1(),parameter.getItem2(),parameter.getItem3());
    }

    protected abstract void execute(double[] probabilities, int index, double reward);
    
}
