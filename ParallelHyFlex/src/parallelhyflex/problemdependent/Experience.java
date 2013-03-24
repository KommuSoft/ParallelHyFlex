package parallelhyflex.problemdependent;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface Experience<TSolution extends Solution<TSolution>,TEC extends EnforceableConstraint<TSolution>> {
    
    public void join (TSolution solution);
    public void amnesia ();
    public Collection<TEC> generateEnforceableConstraints ();
    
}
