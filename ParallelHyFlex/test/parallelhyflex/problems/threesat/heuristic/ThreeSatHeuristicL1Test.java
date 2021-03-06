package parallelhyflex.problems.threesat.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.heuristics.ProblemHeuristicLTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;
import parallelhyflex.problems.threesat.ThreeSatRenewalStrategy;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicL1Test extends ProblemHeuristicLTestBase<ThreeSatSolutionGenerator,ThreeSatProblem,ThreeSatProblemGenerator,ThreeSatSolution> {
    
    /**
     * Test of applyHeuristicLocally method, of class ThreeSatHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyEvaluationApprox() {
        super.testApplyHeuristicLocallyEvaluationApprox();
    }
    
    /**
     *
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyImprovementConflictingClauses() {
        super.testApplyHeuristicLocallyImprovementConflictingClauses();
    }

    /**
     *
     * @return
     */
    @Override
    public LocalSearchHeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic() {
        return new ThreeSatHeuristicL1(getTsp());
    }

    /**
     *
     * @return
     */
    @Override
    public TestHeuristicEvaluationStrategy<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> generateHeuristicEvaluationStrategy() {
        return new ThreeSatEvaluationStrategy();
    }

    /**
     *
     * @return
     */
    @Override
    public TestRenewalStrategy<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> getRenewalStrategy() {
        return new ThreeSatRenewalStrategy();
    }
}