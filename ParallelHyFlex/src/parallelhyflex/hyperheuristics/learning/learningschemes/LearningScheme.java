package parallelhyflex.hyperheuristics.learning.learningschemes;

import parallelhyflex.algebra.Procedure;
import parallelhyflex.algebra.probability.NormalizedProbabilityVector;
import parallelhyflex.algebra.tuples.Tuple3;

/**
 *
 * @author kommusoft
 */
public interface LearningScheme extends Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> {
    
}
