package parallelhyflex.experiencestorage;

import parallelhyflex.algebra.Generator;
import parallelhyflex.problemdependent.Solution;

/**
 *
 * @author kommusoft
 */
public interface InstanceHypothesisGenerator<TSolution extends Solution<TSolution>,THypothesis> extends Generator<THypothesis,TSolution> {
    
}
