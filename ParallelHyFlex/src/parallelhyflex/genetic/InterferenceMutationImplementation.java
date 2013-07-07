/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.genetic;

import java.util.Collection;
import parallelhyflex.interference.InterferenceStructure;

/**
 *
 * @author kommusoft
 */
interface InterferenceMutationImplementation {

    int[] mutate(InterferenceStructure<Integer> interference, int[] input, int[][] ranges);
    
    void mutateLocal(InterferenceStructure<Integer> interference, int[] input, int[][] ranges);
    
    void mutateLocal(InterferenceStructure<Integer> interference, Collection<Integer> modifiedIndices, int[] input, int[][] ranges);
    
    
    
}