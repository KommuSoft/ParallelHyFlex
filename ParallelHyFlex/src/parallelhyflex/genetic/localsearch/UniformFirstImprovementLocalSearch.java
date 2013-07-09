package parallelhyflex.genetic.localsearch;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.utils.IntegerUniqueRandomGenerator;

/**
 *
 * @author kommusoft
 */
public class UniformFirstImprovementLocalSearch extends LocalSearchImplementationBase {

    private static final UniformFirstImprovementLocalSearch instance = new UniformFirstImprovementLocalSearch();

    /**
     *
     * @return
     */
    public static UniformFirstImprovementLocalSearch getInstance() {
        return instance;
    }

    private UniformFirstImprovementLocalSearch() {
    }

    @Override
    protected void localSearchLocalInternal(ManipulationGuider guider, ManipulationObserver observer, int[][] ranges, IntegerUniqueRandomGenerator inputUrg, int[] input) {
        for (Integer index : inputUrg) {
            int[] values = ranges[index];
            int I = values.length;
            int orig = input[index];
            for (int val : values) {
                if (val != orig && guider.calculateDelta(index, val) < 0.0d) {
                    observer.modify(index, val);
                    input[index] = val;
                    inputUrg.reset();
                    break;
                }
            }
        }
    }
}
