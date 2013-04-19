package parallelhyflex.problems.threesat.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicL1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL1Test extends ThreeSatHeuristicLTestBase {

    @Test
    @Override
    public void testApplyHeuristicLocallyConflictingClauses() {
        super.testApplyHeuristicLocallyConflictingClauses();
    }
    
    @Test
    @Override
    public void testApplyHeuristicLocallyImprovementConflictingClauses() {
        super.testApplyHeuristicLocallyImprovementConflictingClauses();
    }

    @Override
    public LocalSearchHeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic() {
        return new ThreeSatHeuristicL1(this.tsp);
    }
    
}