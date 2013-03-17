package parallelhyflex.problemdependent;

import java.util.List;

/**
 *
 * @author kommusoft
 */
public interface EnforceableConstraintExperience<TSolution extends Solution<TSolution>> extends Experience<TSolution> {
    
    List<EnforceableConstraint<TSolution>> generateEnforceableConstraints ();
    
}
