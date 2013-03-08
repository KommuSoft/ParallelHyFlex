package parallelhyflex;

import java.util.Random;

/**
 *
 * @author kommusoft
 */
public abstract class SolutionGeneratorBase<TSolution extends Solution<TSolution>> implements SolutionGenerator<TSolution> {
    
    private final Random random = new Random();
    
    public void setSeed (long seed) {
        this.random.setSeed(seed);
    }
    protected Random getRandom () {
        return this.random;
    }
    
}
