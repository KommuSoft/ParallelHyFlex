package parallelhyflex.genetic;

import java.util.Collection;
import parallelhyflex.interference.InterferenceStructure;

/**
 *
 * @author kommusoft
 */
interface InterferenceCrossoverImplementation {

    int[] crossover(InterferenceStructure<Integer> interference, int[]... parents);
    
    int[] crossover(InterferenceStructure<Integer> interference, int[] genes, int[]... parents);

    void crossoverLocal(InterferenceStructure<Integer> interference, int[]... parents);
    
    void crossoverLocal(InterferenceStructure<Integer> interference, int[] genes, int[]... parents);
    
    void crossoverLocal(InterferenceStructure<Integer> interference, Collection<Integer> modifiedIndices, int[]... parents);
    
    void crossoverLocal(InterferenceStructure<Integer> interference, Collection<Integer> modifiedIndices, int[] genes, int[]... parents);
    
}
