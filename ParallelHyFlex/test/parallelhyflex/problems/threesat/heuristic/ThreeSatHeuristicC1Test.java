/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.threesat.heuristics.ThreeSatHeuristicC1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicC1Test extends ThreeSatHeuristicCTestBase {
    
    public ThreeSatHeuristicC1Test() {
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
        return new ThreeSatHeuristicC1(tsp);
    }
}