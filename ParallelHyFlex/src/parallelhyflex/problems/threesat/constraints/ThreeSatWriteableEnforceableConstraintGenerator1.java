package parallelhyflex.problems.threesat.constraints;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGeneratorBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWriteableEnforceableConstraintGenerator1 extends EnforceableConstraintGeneratorBase<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1> implements ArgumentCloneable<ThreeSatProblem, ThreeSatWriteableEnforceableConstraintGenerator1> {
    
    /**
     *
     * @param problem
     */
    public ThreeSatWriteableEnforceableConstraintGenerator1(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public ThreeSatWriteableEnforceableConstraint1 readAndGenerate(DataInputStream dis) throws IOException {
        long vala = dis.readLong();
        return new ThreeSatWriteableEnforceableConstraint1(this.getProblem(), ThreeSatWriteableEnforceableConstraint1.MASK & vala);
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public ThreeSatWriteableEnforceableConstraintGenerator1 clone(ThreeSatProblem argument) {
        return new ThreeSatWriteableEnforceableConstraintGenerator1(argument);
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatWriteableEnforceableConstraintGenerator1.class.getName());
    
}
