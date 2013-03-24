package parallelhyflex.problemdependent.experience;

import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import java.util.Collection;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface Experience<TSolution extends Solution<TSolution>,TEC extends EnforceableConstraint<TSolution>> {
    
    public void join (TSolution solution);
    public void amnesia ();
    public Collection<TEC> generateEnforceableConstraints ();
    
}
