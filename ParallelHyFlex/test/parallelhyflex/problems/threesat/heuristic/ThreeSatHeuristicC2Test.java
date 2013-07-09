package parallelhyflex.problems.threesat.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.heuristics.ProblemHeuristicCTestBase;
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
public class ThreeSatHeuristicC2Test extends ProblemHeuristicCTestBase<ThreeSatSolutionGenerator,ThreeSatProblem,ThreeSatProblemGenerator,ThreeSatSolution> {
    
    /**
     * Test of applyHeuristicLocally method, of class ThreeSatHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyConflictingClauses() {
        super.testApplyHeuristicLocallyConflictingClauses();
    }

    /**
     *
     * @return
     */
    @Override
    public CrossoverHeuristicBase<ThreeSatSolution, ThreeSatProblem> renewHeuristic() {
        return new ThreeSatHeuristicC2(getTsp());
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