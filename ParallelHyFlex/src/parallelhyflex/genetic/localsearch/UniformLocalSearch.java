package parallelhyflex.genetic.localsearch;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public class UniformLocalSearch extends LocalSearchImplementationBase {

    /**
     *
     * @param guider
     * @param input
     * @param ranges
     * @return
     */
    @Override
    public int[] localSearch(ManipulationGuider guider, int[] input, int[][] ranges) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param guider
     * @param observer
     * @param input
     * @param ranges
     */
    @Override
    public void localSearchLocal(ManipulationGuider guider, ManipulationObserver observer, int[] input, int[][] ranges) {
        boolean modified;
        do {
            modified = false;
            
        }
        while(modified);
    }
    
}
