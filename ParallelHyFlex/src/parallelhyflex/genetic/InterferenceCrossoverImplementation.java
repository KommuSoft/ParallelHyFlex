package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationObserver;
import java.util.Collection;
import parallelhyflex.interference.InterferenceStructure;

/**
 *
 * @author kommusoft
 */
interface InterferenceCrossoverImplementation extends CrossoverImplementation {

    int[] crossover(InterferenceStructure<Integer> interference, int[]... parents);

    int[] crossover(InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents);

    void crossoverLocal(InterferenceStructure<Integer> interference, int[]... parents);

    void crossoverLocal(InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents);

    void crossoverLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, int[]... parents);

    void crossoverLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, Collection<Integer> genes, int[]... parents);

    void crossoverLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[]... parents);

    void crossoverLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents);
}
