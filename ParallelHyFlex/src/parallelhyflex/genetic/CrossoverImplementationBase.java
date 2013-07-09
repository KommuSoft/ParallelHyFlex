package parallelhyflex.genetic;

import java.util.Collection;
import parallelhyflex.algebra.collections.ConstantInfiniteCollection;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;

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
        return this.crossover(0x01, parents);
    }

    /**
     *
     * @param genes 
     * @param parents
     * @return
     */
    @Override
    public int[] crossover(int genes, int[]... parents) {
        return this.crossover(new ConstantInfiniteCollection<>(genes), parents);
    }

    /**
     *
     * @param parents
     */
    @Override
    public void crossoverLocal(int[]... parents) {
        this.crossoverLocal(0x01, parents);
    }

    /**
     *
     * @param genes 
     * @param parents
     */
    @Override
    public void crossoverLocal(int genes, int[]... parents) {
        this.crossoverLocal(new ConstantInfiniteCollection<>(genes), parents);
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

    /**
     *
     * @param observer
     * @param genes 
     * @param parents
     */
    @Override
    public void crossoverLocal(ManipulationObserver observer, int genes, int[]... parents) {
        this.crossoverLocal(observer, new ConstantInfiniteCollection<>(genes), parents);
    }
}
