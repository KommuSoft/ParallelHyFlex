package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problemdependent.heuristics.RuinRecreateHeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicRTestBase extends ThreeSatHeuristicTestBase {
    
    @Override
    public abstract RuinRecreateHeuristicBase<ThreeSatSolution,ThreeSatProblem> renewHeuristic ();
    
}
