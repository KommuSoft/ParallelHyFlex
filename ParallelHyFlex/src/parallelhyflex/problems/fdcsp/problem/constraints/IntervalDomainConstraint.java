package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.problems.fdcsp.problem.Interval;

/**
 *
 * @author kommusoft
 */
public interface IntervalDomainConstraint {
    
    void reduceDomains (Interval i1, Interval i2);
    
}
