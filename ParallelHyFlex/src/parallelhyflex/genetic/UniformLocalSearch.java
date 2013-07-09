package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationGuider;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @author kommusoft
 */
public class UniformLocalSearch extends LocalSearchImplementationBase {

    @Override
    public int[] localSearch(ManipulationGuider guider, int[] input, int[][] ranges) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void localSearchLocal(ManipulationGuider guider, ManipulationObserver observer, int[] input, int[][] ranges) {
        boolean modified;
        do {
            modified = false;
            
        }
        while(modified);
    }
    
}
