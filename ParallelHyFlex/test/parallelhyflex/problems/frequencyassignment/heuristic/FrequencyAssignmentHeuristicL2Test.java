package parallelhyflex.problems.frequencyassignment.heuristic;

import org.junit.Test;
import parallelhyflex.problemdependent.heuristic.LocalSearchHeuristicBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;
import parallelhyflex.problems.heuristics.ProblemHeuristicLTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentHeuristicL2Test extends ProblemHeuristicLTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> {

    /**
     * Test of applyHeuristicLocally method, of class
     * FrequencyAssignmentHeuristicM1.
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
    public LocalSearchHeuristicBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> renewHeuristic() {
        return new FrequencyAssignmentHeuristicL2(getTsp());
    }

    /**
     *
     * @return
     */
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