/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
public interface CrossoverImplementation {

    int[] crossover(int[] genes, int[]... parents);

    int[] crossover(int[]... parents);
    
}
