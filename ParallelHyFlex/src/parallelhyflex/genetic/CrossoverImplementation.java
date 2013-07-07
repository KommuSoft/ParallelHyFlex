/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.genetic;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface CrossoverImplementation {

    int[] crossover(int[] genes, int[]... parents);

    void crossoverLocal(int[] genes, int[]... parents);

    void crossoverLocal(Collection<Integer> modifiedIndices, int[] genes, int[]... parents);

    int[] crossover(int[]... parents);

    void crossoverLocal(int[]... parents);

    void crossoverLocal(Collection<Integer> modifiedIndices, int[]... parents);
}
