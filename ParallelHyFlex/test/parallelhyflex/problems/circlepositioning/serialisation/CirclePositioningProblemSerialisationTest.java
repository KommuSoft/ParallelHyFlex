package parallelhyflex.problems.circlepositioning.serialisation;

import java.io.IOException;
import org.junit.Test;
import parallelhyflex.problems.TestRenewalStrategy;
import parallelhyflex.problems.circlepositioning.CirclePositioningRenewalStrategy;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolutionGenerator;
import parallelhyflex.problems.serialisation.ProblemSerialisationTest;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningProblemSerialisationTest extends ProblemSerialisationTest<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> {

    @Test
    @Override
    public void testSerializationDeserialization() throws IOException {
        super.testSerializationDeserialization();
    }

    @Override
    public TestRenewalStrategy<CirclePositioningSolutionGenerator, CirclePositioningProblem, CirclePositioningProblemGenerator, CirclePositioningSolution> getRenewalStrategy() {
        return new CirclePositioningRenewalStrategy();
    }
}
