package parallelhyflex.genetic;

import parallelhyflex.interference.InterferenceStructure;

/**
 *
 * @author kommusoft
 */
interface InterferenceCrossoverImplementation {

    int[] crossover(InterferenceStructure<Integer> interference, int[]... parents);
    
}
