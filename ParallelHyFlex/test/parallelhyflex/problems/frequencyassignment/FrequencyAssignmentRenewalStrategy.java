package parallelhyflex.problems.frequencyassignment;

import parallelhyflex.problems.ProblemTestBase;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentRenewalStrategy implements TestRenewalStrategy<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> {

    /**
     *
     * @param testbase
     */
    @Override
    public void renewProblem(ProblemTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> testbase) {
        testbase.setTsp(testbase.getTspg().generateProblem());
    }

    /**
     *
     * @param testbase
     */
    @Override
    public void renewProblemGenerator(ProblemTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> testbase) {
        testbase.setTspg(new FrequencyAssignmentProblemGenerator());
    }

    /**
     *
     * @param testbase
     */
    @Override
    public void renewSolution(ProblemTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> testbase) {
        testbase.setTss(testbase.getTsg().generateSolution());
    }

    /**
     *
     * @param testbase
     */
    @Override
    public void renewSolutionGenerator(ProblemTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> testbase) {
        testbase.setTsg(testbase.getTsp().getSolutionGenerator());
    }
}