package parallelhyflex.problemdependent.solution;

import parallelhyflex.communication.serialisation.ReadableGenerator;

/**
 *
 * @author kommusoft
 */
public interface SolutionReader<TSolution extends Solution<TSolution>> extends ReadableGenerator<TSolution> {
}
