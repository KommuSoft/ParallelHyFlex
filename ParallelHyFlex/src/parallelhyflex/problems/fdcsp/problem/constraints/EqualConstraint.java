package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.problems.fdcsp.problem.Interval;

/**
 *
 * @author kommusoft
 */
@ProgrammedConstraints(operator="#=")
public class EqualConstraint implements IntervalDomainConstraint {

    @Override
    public void reduceDomains(Interval i1, Interval i2) {
        i1.intersectWith(i2);
        i2.intersectWith(i1);
    }
    
}
