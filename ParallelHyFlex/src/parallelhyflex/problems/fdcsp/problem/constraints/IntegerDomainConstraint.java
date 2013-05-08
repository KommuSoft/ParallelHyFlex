package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
public interface IntegerDomainConstraint {
    
    boolean reduceDomains (MutableFiniteIntegerDomain i1, MutableFiniteIntegerDomain i2);
    boolean reduceDomains (int i1, MutableFiniteIntegerDomain i2);
    boolean reduceDomains (MutableFiniteIntegerDomain i1, int i2);
    
}
