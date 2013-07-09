package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationGuiderObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;

public abstract class LocalSearchImplementationBase implements LocalSearchImplementation {

    @Override
    public void localSearchLocal(ManipulationGuider guider, int[] input, int[][] ranges) {
        this.localSearchLocal(guider, NullManipulationObserver.getInstance(), input, ranges);
    }

    @Override
    public void localSearchLocal(ManipulationGuiderObserver guiderObserver, int[] input, int[][] ranges) {
        this.localSearchLocal(guiderObserver, guiderObserver, input, ranges);
    }
}
