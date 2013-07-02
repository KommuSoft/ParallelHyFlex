/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.circlepositioning.heuristics;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningHeuristicM1Test extends CirclePositioningHeuristicMTestBase {
    
    /**
     * Test of applyHeuristicLocally method, of class CirclePositioningHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyConflictingClauses() {
        super.testApplyHeuristicLocallyConflictingClauses();
    }

    @Override
    public MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> renewHeuristic() {
        return new CirclePositioningHeuristicM1(tsp);
    }
}