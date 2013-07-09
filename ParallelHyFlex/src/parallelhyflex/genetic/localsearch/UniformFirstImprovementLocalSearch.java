package parallelhyflex.genetic.localsearch;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.IntegerUniqueRandomGenerator;

/**
 *
 * @author kommusoft
 */
public class UniformFirstImprovementLocalSearch extends LocalSearchImplementationBase {

    @Override
    protected boolean localSearchIteration(ManipulationGuider guider, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] cache, int[] cache2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean localSearchLocalIteration(ManipulationGuider guider, ManipulationObserver observer, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] input) {
        for (Integer index : inputUrg) {
            int[] values = ranges[index];
            int I = values.length;
            int orig = input[index];
            for (int val : values) {
                if (val != orig && guider.calculateDelta(index, val) > 0x00) {
                    observer.modify(index, val);
                }
            }
        }
        return false;
    }
}
