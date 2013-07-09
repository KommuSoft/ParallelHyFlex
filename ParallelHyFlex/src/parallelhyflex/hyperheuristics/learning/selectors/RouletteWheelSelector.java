package parallelhyflex.hyperheuristics.learning.selectors;

import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class RouletteWheelSelector implements Selector {

    /**
     *
     * @param probabilities
     * @return
     */
    @Override
    public Integer generate(double[] probabilities) {
        double p = Utils.nextDouble();
        int index = 0;
        while(index < probabilities.length && p > probabilities[index]) {
            p -= probabilities[index++];
        }
        return Math.min(index,probabilities.length-1);
    }
    
}
