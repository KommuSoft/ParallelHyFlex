package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
interface InterferenceCrossoverImplementation {

    int[] crossover(InterferenceStructure<Integer> interference, int[]... parents);
    
}
