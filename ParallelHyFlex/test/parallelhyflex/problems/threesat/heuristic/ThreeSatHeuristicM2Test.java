/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicM2;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM2Test extends ThreeSatHeuristicMTestBase {

    /**
     * Test of applyHeuristicLocally method, of class ThreeSatHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyConflictingClauses() {
        super.testApplyHeuristicLocallyConflictingClauses();
    }

    @Override
    public MutationHeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic() {
        return new ThreeSatHeuristicM2(tsp);
    }
}