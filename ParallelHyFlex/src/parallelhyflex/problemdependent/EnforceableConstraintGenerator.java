package parallelhyflex.problemdependent;

import parallelhyflex.communication.ReadableGenerator;

/**
 *
 * @author kommusoft
 */
public interface EnforceableConstraintGenerator<TSolution extends Solution<TSolution>,TEC extends EnforceableConstraint<TSolution>> extends ReadableGenerator<TEC> {
    
}
