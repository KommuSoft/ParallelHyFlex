package parallelhyflex.problemdependent.problem;

import parallelhyflex.communication.serialisation.ReadableGenerator;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface ProblemReader<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ReadableGenerator<TProblem> {
}
