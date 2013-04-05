/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.LocalSearchHeuristicBase;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicL3;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL3Test extends ThreeSatHeuristicLTestBase {

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
        return new ThreeSatHeuristicL3(this.tsp);
    }
    
}