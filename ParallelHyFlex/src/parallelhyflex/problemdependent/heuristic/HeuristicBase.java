package parallelhyflex.problemdependent.heuristic;

import parallelhyflex.algebra.ProblemPointer;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class HeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends Heuristic<TSolution> implements ProblemPointer<TSolution, TProblem> {

    private final TProblem problem;

    /**
     *
     * @param problem
     * @param type
     */
    public HeuristicBase(TProblem problem, HeuristicType type) {
        super(type);
        this.problem = problem;
    }

    /**
     *
     * @param type
     * @param problem
     */
    public HeuristicBase(HeuristicType type, TProblem problem) {
        this(problem,type);
    }

    /**
     * @return the problem
     */
    @Override
    public TProblem getProblem() {
        return problem;
    }

    /**
     *
     * @return
     */
    public double getIntensityOfMutation() {
        return this.getProblem().getIntensityOfMutation();
    }

    /**
     *
     * @return
     */
    public double getDepthOfSearch() {
        return this.getProblem().getDepthOfSearch();
    }
}
