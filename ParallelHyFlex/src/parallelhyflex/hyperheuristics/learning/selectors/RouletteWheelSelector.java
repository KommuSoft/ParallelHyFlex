package parallelhyflex.hyperheuristics.learning.selectors;

import java.util.logging.Logger;
import parallelhyflex.algebra.probability.NormalizedProbabilityVector;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class RouletteWheelSelector implements Selector {

    @Override
    public Integer generate(NormalizedProbabilityVector probabilities) {
        double p = Utils.StaticRandom.nextDouble();
        int index = 0;
        int length = probabilities.getSize();
        double prob = probabilities.getProbability(index);
        while (index < length && p > prob) {
            p -= prob;
            prob = probabilities.getProbability(++index);
        }
        return Math.min(index, length - 1);
    }
    private static final Logger LOG = Logger.getLogger(RouletteWheelSelector.class.getName());
}
