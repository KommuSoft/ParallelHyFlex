package parallelhyflex.problems.threesat.serialisation;

import java.io.IOException;
import org.junit.Test;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.serialisation.ProblemSerialisationTest;
import parallelhyflex.problems.threesat.ThreeSatRenewalStrategy;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemSerialisationTest extends ProblemSerialisationTest<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> {

    @Test
    @Override
    public void testSerializationDeserialization() throws IOException {
        super.testSerializationDeserialization();
    }

    @Override
    public TestRenewalStrategy<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> getRenewalStrategy() {
        return new ThreeSatRenewalStrategy();
    }
}
