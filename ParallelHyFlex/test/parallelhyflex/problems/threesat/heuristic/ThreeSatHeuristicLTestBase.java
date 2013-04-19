package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicLTestBase extends ThreeSatHeuristicTestBase {
    
    @Override
    public abstract LocalSearchHeuristicBase<ThreeSatSolution,ThreeSatProblem> renewHeuristic ();
    
}
