package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
public interface IntegerDomainConstraint {
    
    void reduceDomains (FiniteIntegerDomain i1, FiniteIntegerDomain i2);
    
}
