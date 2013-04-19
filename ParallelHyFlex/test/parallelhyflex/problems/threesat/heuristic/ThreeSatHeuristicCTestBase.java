package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicCTestBase extends ThreeSatHeuristicTestBase {
    
    @Override
    public abstract CrossoverHeuristicBase<ThreeSatSolution,ThreeSatProblem> renewHeuristic ();
    
    @Override
    public void applyHeuristic() {
        ThreeSatSolution tss2 = this.tsg.generateSolution();
        hb.applyHeuristicLocally(tss2);
    }
    
}