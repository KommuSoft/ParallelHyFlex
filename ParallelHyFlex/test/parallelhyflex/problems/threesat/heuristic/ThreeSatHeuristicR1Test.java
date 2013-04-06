/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.RuinRecreateHeuristicBase;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicR1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicR1Test extends ThreeSatHeuristicRTestBase {
    
    /**
     * Test of applyHeuristicLocally method, of class ThreeSatHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyConflictingClauses() {
        super.testApplyHeuristicLocallyConflictingClauses();
    }

    @Override
    public RuinRecreateHeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic() {
        return new ThreeSatHeuristicR1(tsp);
    }
}