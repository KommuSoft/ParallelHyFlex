package parallelhyflex.problemdependent;

import java.util.Random;

/**
 *
 * @author kommusoft
 */
public abstract class SolutionGeneratorBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> implements SolutionGenerator<TSolution> {
    
    private final Random random = new Random();
    private final TProblem problem;
    
    public SolutionGeneratorBase (TProblem problem) {
        this.problem = problem;
    }
    
    public void setSeed (long seed) {
        this.random.setSeed(seed);
    }
    protected Random getRandom () {
        return this.random;
    }

    /**
     * @return the problem
     */
    public TProblem getProblem() {
        return problem;
    }
    
}
