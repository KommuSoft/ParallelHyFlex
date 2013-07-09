package parallelhyflex.problems.threesat.constraint;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGeneratorBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraintGenerator extends EnforceableConstraintGeneratorBase<ThreeSatSolution, ThreeSatProblem, EnforceableConstraint<ThreeSatSolution>> implements ArgumentCloneable<ThreeSatProblem, ThreeSatWritableEnforceableConstraintGenerator> {

    /**
     *
     * @param problem
     */
    public ThreeSatWritableEnforceableConstraintGenerator(ThreeSatProblem problem) {
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
        if ((vala & ThreeSatWritableEnforceableConstraint1.MASK_BIT) != 0x00) {
            return new ThreeSatWritableEnforceableConstraint1(this.getProblem(), ThreeSatWritableEnforceableConstraint1.MASK & vala);
        } else {
            int distance = (int) vala;
            ThreeSatSolution tss = this.getProblem().getSolutionGenerator().readAndGenerate(dis);
            return new ThreeSatWritableEnforceableConstraint2(this.getProblem(), tss, distance);
        }
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public ThreeSatWritableEnforceableConstraintGenerator clone(ThreeSatProblem argument) {
        return new ThreeSatWritableEnforceableConstraintGenerator(argument);
    }
}
