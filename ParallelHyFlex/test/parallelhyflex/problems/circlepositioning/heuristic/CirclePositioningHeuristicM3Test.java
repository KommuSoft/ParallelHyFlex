package parallelhyflex.problems.circlepositioning.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristic.MutationHeuristicBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.circlepositioning.CirclePositioningRenewalStrategy;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;
import parallelhyflex.problems.heuristics.ProblemHeuristicMTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningHeuristicM3Test extends ProblemHeuristicMTestBase<CirclePositioningSolutionGenerator,CirclePositioningProblem,CirclePositioningProblemGenerator,CirclePositioningSolution> {
    
    /**
     * Test of applyHeuristicLocally method, of class CirclePositioningHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyEvaluationApprox() {
        super.testApplyHeuristicLocallyEvaluationApprox();
    }

    /**
     *
     * @return
     */
    @Override
    public MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> renewHeuristic() {
        return new CirclePositioningHeuristicM3(getTsp());
    }

    /**
     *
     * @return
     */
    @Override
    public TestHeuristicEvaluationStrategy<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> generateHeuristicEvaluationStrategy() {
        return new CirclePositioningEvaluationStrategy();
    }

    /**
     *
     * @return
     */
    @Override
    public TestRenewalStrategy<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> getRenewalStrategy() {
        return new CirclePositioningRenewalStrategy();
    }
}