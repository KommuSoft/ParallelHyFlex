package parallelhyflex.problems.circlepositioning.heuristics;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningHeuristicM2Test extends CirclePositioningHeuristicMTestBase {
    
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
        return new CirclePositioningHeuristicM2(getTsp());
    }
}