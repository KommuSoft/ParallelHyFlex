package parallelhyflex.experiencestorage;

import parallelhyflex.algebra.Generator;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface InstanceHypothesisGenerator<TSolution extends Solution<TSolution>, THypothesis> extends Generator<TSolution, THypothesis> {
}
