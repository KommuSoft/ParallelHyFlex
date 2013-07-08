package parallelhyflex.genetic;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface CrossoverImplementation {

    int[] crossover(int[] genes, int[]... parents);

    void crossoverLocal(int[] genes, int[]... parents);
    
    void crossoverLocal(ManipulationObserver observer, int[] genes, int[]... parents);

    int[] crossover(int[]... parents);

    void crossoverLocal(int[]... parents);

    void crossoverLocal(ManipulationObserver observer, int[]... parents);
}
