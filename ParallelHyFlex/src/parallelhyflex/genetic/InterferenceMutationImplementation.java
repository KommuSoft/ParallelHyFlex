/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
interface InterferenceMutationImplementation {

    int[] mutate(InterferenceStructure<Integer> interference, int[] input, int[][] ranges);
    
}
