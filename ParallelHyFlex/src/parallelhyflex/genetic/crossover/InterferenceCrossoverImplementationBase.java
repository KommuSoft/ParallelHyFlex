package parallelhyflex.genetic.crossover;

import java.util.Collection;
import parallelhyflex.algebra.collections.ConstantInfiniteCollection;
import parallelhyflex.genetic.observer.ManipulationObserver;
import parallelhyflex.genetic.observer.NullManipulationObserver;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.interference.NoInterferenceStructure;

/**
 *
 * @author kommusoft
 */
public abstract class InterferenceCrossoverImplementationBase extends CrossoverImplementationBase implements InterferenceCrossoverImplementation {

    /**
     *
     * @param interference
     * @param genes
     * @param parents
     */
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(interference, NullManipulationObserver.getInstance(), genes, parents);
    }

    /**
     *
     * @param interference
     * @param parents
     */
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, int[]... parents) {
        this.crossoverLocal(interference, NullManipulationObserver.getInstance(), parents);
    }

    /**
     *
     * @param interference
     * @param parents
     * @return
     */
    @Override
    public int[] crossover(InterferenceStructure<Integer> interference, int[]... parents) {
        return this.crossover(interference, new ConstantInfiniteCollection<>(1), parents);
    }

    /**
     *
     * @param interference
     * @param observer
     * @param parents
     */
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, int[]... parents) {
        this.crossoverLocal(interference, observer, new ConstantInfiniteCollection<>(0x01), parents);
    }

    /**
     *
     * @param observer
     * @param interference
     * @param parents
     */
    @Override
    public void crossoverLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[]... parents) {
        this.crossoverLocal(interference, observer, parents);
    }

    /**
     *
     * @param observer
     * @param interference
     * @param genes
     * @param parents
     */
    @Override
    public void crossoverLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(interference, observer, genes, parents);
    }

    /**
     *
     * @param genes
     * @param parents
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public int[] crossover(Collection<Integer> genes, int[]... parents) {
        return this.crossover(NoInterferenceStructure.getInstance(), genes, parents);
    }

    /**
     *
     * @param observer
     * @param genes
     * @param parents
     */
    @Override
    @SuppressWarnings("unchecked")
    public void crossoverLocal(ManipulationObserver observer, Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(NoInterferenceStructure.getInstance(), observer, genes, parents);
    }
}
