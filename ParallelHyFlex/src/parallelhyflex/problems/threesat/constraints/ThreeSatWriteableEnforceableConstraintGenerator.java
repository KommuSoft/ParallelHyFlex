package parallelhyflex.problems.threesat.constraints;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGeneratorBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWriteableEnforceableConstraintGenerator extends EnforceableConstraintGeneratorBase<ThreeSatSolution, ThreeSatProblem, EnforceableConstraint<ThreeSatSolution>> implements ArgumentCloneable<ThreeSatProblem, ThreeSatWriteableEnforceableConstraintGenerator> {

    /**
     *
     * @param problem
     */
    public ThreeSatWriteableEnforceableConstraintGenerator(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public EnforceableConstraint<ThreeSatSolution> readAndGenerate(DataInputStream dis) throws IOException {
        long vala = dis.readLong();
        if ((vala & ThreeSatWriteableEnforceableConstraint1.MASK_BIT) != 0x00) {
            return new ThreeSatWriteableEnforceableConstraint1(this.getProblem(), ThreeSatWriteableEnforceableConstraint1.MASK & vala);
        } else {
            int distance = (int) vala;
            ThreeSatSolution tss = this.getProblem().getSolutionGenerator().readAndGenerate(dis);
            return new ThreeSatWriteableEnforceableConstraint2(this.getProblem(), tss, distance);
        }
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public ThreeSatWriteableEnforceableConstraintGenerator clone(ThreeSatProblem argument) {
        return new ThreeSatWriteableEnforceableConstraintGenerator(argument);
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatWriteableEnforceableConstraintGenerator.class.getName());
}
