package parallelhyflex.problemdependent.heuristics;

import parallelhyflex.ProblemPointer;
import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class HeuristicBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends Heuristic<TSolution> implements ProblemPointer<TSolution,TProblem> {
    
    private final TProblem problem;
    
    public HeuristicBase (TProblem problem, HeuristicType type) {
        super(type);
        this.problem = problem;
    }
    public HeuristicBase (HeuristicType type, TProblem problem) {
        super(type);
        this.problem = problem;
    }
    
    /**
     * @return the problem
     */
    public TProblem getProblem() {
        return problem;
    }
    
    public double getIntensityOfMutation () {
        return this.getProblem().getIntensityOfMutation();
    }
    public double getDepthOfSearch () {
        return this.getProblem().getDepthOfSearch();
    }
    
}
