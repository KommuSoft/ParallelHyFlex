package parallelhyflex.problems.frequencyassignment.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristic.CrossoverHeuristicBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;
import parallelhyflex.problems.heuristics.ProblemHeuristicCTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicC3Test extends ProblemHeuristicCTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> {

    /**
     * Test of applyHeuristicLocally method, of class
     * FrequencyAssignmentHeuristicM1.
     */
    @Test
    @Override
    public void testApplyHeuristicLocallyEvaluationApprox() {
        super.testApplyHeuristicLocallyEvaluationApprox();
    }

    @Override
    public CrossoverHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> renewHeuristic() {
        return new FrequencyAssignmentHeuristicC3(getTsp());
    }

    @Override
    public TestHeuristicEvaluationStrategy<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> generateHeuristicEvaluationStrategy() {
        return new FrequencyAssignmentEvaluationStrategy();
    }

    /**
     *
     * @return
     */
    @Override
    public TestRenewalStrategy<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> getRenewalStrategy() {
        return new FrequencyAssignmentRenewalStrategy();
    }
}