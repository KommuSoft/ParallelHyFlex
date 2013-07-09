/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.genetic;

import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;
import java.util.Collection;
import parallelhyflex.algebra.collections.ConstantInfiniteCollection;

/**
 *
 * @author kommusoft
 */
public abstract class CrossoverImplementationBase implements CrossoverImplementation {

    /**
     *
     * @param genes
     * @param parents
     */
    @Override
    public void crossoverLocal(Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(NullManipulationObserver.getInstance(), genes, parents);
    }

    /**
     *
     * @param parents
     * @return
     */
    @Override
    public int[] crossover(int[]... parents) {
        return this.crossover(new ConstantInfiniteCollection<>(0x01), parents);
    }

    /**
     *
     * @param parents
     */
    @Override
    public void crossoverLocal(int[]... parents) {
        this.crossoverLocal(new ConstantInfiniteCollection<>(0x01), parents);
    }

    /**
     *
     * @param observer
     * @param parents
     */
    @Override
    public void crossoverLocal(ManipulationObserver observer, int[]... parents) {
        this.crossoverLocal(observer, new ConstantInfiniteCollection<>(0x01), parents);
    }
}
