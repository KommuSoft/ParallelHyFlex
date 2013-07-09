package parallelhyflex.problemdependent.solution;

import java.util.Random;
import parallelhyflex.algebra.ProblemPointerBase;
import parallelhyflex.problemdependent.problem.Problem;

/**
 *
 * @author kommusoft
 */
public abstract class SolutionGeneratorBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution, TProblem> implements SolutionGenerator<TSolution> {

    private final Random random = new Random();

    /**
     *
     * @param problem
     */
    public SolutionGeneratorBase(TProblem problem) {
        super(problem);
    }

    /**
     *
     * @param seed
     */
    @Override
    public void setSeed(long seed) {
        this.random.setSeed(seed);
    }

    /**
     *
     * @return
     */
    protected Random getRandom() {
        return this.random;
    }
}
