package parallelhyflex.problemdependent.searchspace;

import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.algebra.ProblemPointerBase;

/**
 *
 * @author kommusoft
 */
public abstract class SearchSpaceBase<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements SearchSpace<TSolution> {
    
    public SearchSpaceBase (TProblem problem) {
        super(problem);
    }
    
    @Override
    public boolean isNotInSearchSpace(TSolution solution) {
        return !this.isInSearchSpace(solution);
    }
    
}
