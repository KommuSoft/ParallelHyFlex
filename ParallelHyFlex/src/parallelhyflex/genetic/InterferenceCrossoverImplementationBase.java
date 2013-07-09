package parallelhyflex.genetic;

import java.util.Collection;
import parallelhyflex.algebra.collections.ConstantInfiniteCollection;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.interference.NoInterferenceStructure;

/**
 *
 * @author kommusoft
 */
public abstract class InterferenceCrossoverImplementationBase implements InterferenceCrossoverImplementation {
    
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(interference, NullManipulationObserver.getInstance(), genes, parents);
    }
    
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, int[]... parents) {
        this.crossoverLocal(interference, NullManipulationObserver.getInstance(), parents);
    }
    
    @Override
    public int[] crossover(InterferenceStructure<Integer> interference, int[]... parents) {
        return this.crossover(interference, new ConstantInfiniteCollection<>(1), parents);
    }
    
    @Override
    public void crossoverLocal(InterferenceStructure<Integer> interference, ManipulationObserver observer, int[]... parents) {
        this.crossoverLocal(interference, observer, new ConstantInfiniteCollection<>(0x01), parents);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public int[] crossover(int[]... parents) {
        return this.crossover(NoInterferenceStructure.getInstance(), parents);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void crossoverLocal(int[]... parents) {
        this.crossoverLocal(NoInterferenceStructure.getInstance(), parents);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void crossoverLocal(ManipulationObserver observer, int[]... parents) {
        this.crossoverLocal(NoInterferenceStructure.getInstance(), observer, parents);
    }
    
    @Override
    public void crossoverLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, int[]... parents) {
        this.crossoverLocal(interference, observer, parents);
    }
    
    @Override
    public void crossoverLocal(ManipulationObserver observer, InterferenceStructure<Integer> interference, Collection<Integer> genes, int[]... parents) {
        this.crossoverLocal(interference, observer, genes, parents);
    }
}
