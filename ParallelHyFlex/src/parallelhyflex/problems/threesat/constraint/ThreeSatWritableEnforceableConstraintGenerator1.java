package parallelhyflex.problems.threesat.constraint;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGeneratorBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraintGenerator1 extends EnforceableConstraintGeneratorBase<ThreeSatSolution, ThreeSatProblem, ThreeSatWritableEnforceableConstraint1> implements ArgumentCloneable<ThreeSatProblem, ThreeSatWritableEnforceableConstraintGenerator1> {
    
    public ThreeSatWritableEnforceableConstraintGenerator1(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public ThreeSatWritableEnforceableConstraint1 readAndGenerate(DataInputStream dis) throws IOException {
        long vala = dis.readLong();
        return new ThreeSatWritableEnforceableConstraint1(this.getProblem(), ThreeSatWritableEnforceableConstraint1.MASK & vala);
    }

    @Override
    public ThreeSatWritableEnforceableConstraintGenerator1 clone(ThreeSatProblem argument) {
        return new ThreeSatWritableEnforceableConstraintGenerator1(argument);
    }
    
}
