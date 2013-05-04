package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.parsing.Operator;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;

/**
 *
 * @author kommusoft
 */
@Operator(token = "#<")
public class SmallerThanConstraint implements IntegerDomainConstraint {

    @Override
    public void reduceDomains(FiniteIntegerDomain i1, FiniteIntegerDomain i2) {
        //TODO
    }
}