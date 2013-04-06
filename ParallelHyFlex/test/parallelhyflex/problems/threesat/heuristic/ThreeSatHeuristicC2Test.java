/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicC2;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicC2Test extends ThreeSatHeuristicCTestBase {
    
    public ThreeSatHeuristicC2Test() {
    }

    /**
     * Test of applyHeuristicLocally method, of class ThreeSatHeuristicC1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyConflictingClauses() {
        super.testApplyHeuristicLocallyConflictingClauses();
    }

    @Override
    public CrossoverHeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic() {
        return new ThreeSatHeuristicC2(tsp);
    }
}