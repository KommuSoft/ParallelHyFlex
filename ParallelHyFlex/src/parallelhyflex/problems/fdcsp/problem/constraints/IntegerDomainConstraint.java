package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
public interface IntegerDomainConstraint {
    
    boolean reduceDomains (FiniteIntegerDomain i1, FiniteIntegerDomain i2);
    boolean reduceDomains (int i1, FiniteIntegerDomain i2);
    boolean reduceDomains (FiniteIntegerDomain i1, int i2);
    
}
