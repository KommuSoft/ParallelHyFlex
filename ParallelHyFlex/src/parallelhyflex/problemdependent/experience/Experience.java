package parallelhyflex.problemdependent.experience;

import java.util.Collection;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @param <TSolution>
 * @param <TEC>
 * @author kommusoft
 */
public interface Experience<TSolution extends Solution<TSolution>, TEC extends EnforceableConstraint<TSolution>> {

    public void join(TSolution solution);

    public void amnesia();

    public Collection<TEC> generateEnforceableConstraints();
}
