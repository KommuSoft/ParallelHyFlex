package parallelhyflex.problemdependent.constraints;

import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface Constraint<TSolution extends Solution<TSolution>> {

    boolean isSatisfied(TSolution solution);

    boolean isNotSatisfied(TSolution solution);
}
