package parallelhyflex.problemdependent.solution;

import parallelhyflex.communication.ReadableGenerator;

/**
 *
 * @author kommusoft
 */
public interface SolutionReader<TSolution extends Solution<TSolution>> extends ReadableGenerator<TSolution> {
}
