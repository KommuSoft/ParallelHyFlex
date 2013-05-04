package parallelhyflex.problems.fdcsp.problem.constraints;

import parallelhyflex.problems.fdcsp.problem.Interval;

/**
 *
 * @author kommusoft
 */
@ProgrammedConstraints(operator="#\\=")
public class NotEqualConstraint implements IntervalDomainConstraint {

    @Override
    public void reduceDomains(Interval i1, Interval i2) {
        //TODO
    }
}
