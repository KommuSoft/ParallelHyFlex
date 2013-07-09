package parallelhyflex.genetic.localsearch;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.IntegerUniqueRandomGenerator;

/**
 *
 * @author kommusoft
 */
public class UniformBestImprovementLocalSearch extends LocalSearchImplementationBase {

    @Override
    protected void localSearchLocalInternal(ManipulationGuider guider, ManipulationObserver observer, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] input) {
        for (Integer index : inputUrg) {
            int[] values = ranges[index];
            int I = values.length;
            int orig = input[index];
            double bestdelta = 0.0d;
            int bestval = -0x01;
            for (int val : values) {
                if (val != orig) {
                    double delta = guider.calculateDelta(index, val);
                    if (delta > bestdelta) {
                        bestdelta = delta;
                        bestval = val;
                    }
                }
            }
            if(bestval > 0.0d) {
                observer.modify(index,bestval);
                input[index] = bestval;
                inputUrg.reset();
            }
        }
    }
}
