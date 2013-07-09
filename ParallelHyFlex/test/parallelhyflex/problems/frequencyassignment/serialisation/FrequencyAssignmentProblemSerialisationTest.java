package parallelhyflex.problems.frequencyassignment.serialisation;

import java.io.IOException;
import org.junit.Test;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.FrequencyAssignmentRenewalStrategy;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;
import parallelhyflex.problems.serialisation.ProblemSerialisationTest;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentProblemSerialisationTest extends ProblemSerialisationTest<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> {

    /**
     *
     * @throws IOException
     */
    @Test
    @Override
    public void testSerializationDeserialization() throws IOException {
        super.testSerializationDeserialization();
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
