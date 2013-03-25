package parallelhyflex.problemdependent.solution;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.algebra.ProblemPointerBase;
import java.util.Random;

/**
 *
 * @author kommusoft
 */
public abstract class SolutionGeneratorBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements SolutionGenerator<TSolution> {
    
    private final Random random = new Random();
    
    public SolutionGeneratorBase (TProblem problem) {
        super(problem);
    }
    
    public void setSeed (long seed) {
        this.random.setSeed(seed);
    }
    protected Random getRandom () {
        return this.random;
    }
    
}