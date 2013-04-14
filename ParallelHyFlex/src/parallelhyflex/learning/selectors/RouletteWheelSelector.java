package parallelhyflex.learning.selectors;

import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class RouletteWheelSelector implements Selector {

    @Override
    public Integer generate(double[] probabilities) {
        double p = Utils.StaticRandom.nextDouble();
        int index = 0;
        while(p > probabilities[index]) {
            p -= probabilities[index++];
        }
        return index;
    }
    
}
