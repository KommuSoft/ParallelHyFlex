package parallelhyflex;

import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class ProblemPointerBase<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> implements ProblemPointer<TSolution,TProblem> {
    
    private final TProblem problem;
    
    public ProblemPointerBase (TProblem problem) {
        this.problem = problem;
    }
    
    @Override
    public TProblem getProblem () {
        return problem;
    }
    
}