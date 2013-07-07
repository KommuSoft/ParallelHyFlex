/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
public interface MutationImplementation {

    int[] mutate(int[] input, int[][] ranges);
    
    void mutateLocal(int[] input, int[][] ranges);
    
}
