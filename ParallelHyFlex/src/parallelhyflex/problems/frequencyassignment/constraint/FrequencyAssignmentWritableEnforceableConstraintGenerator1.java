package parallelhyflex.problems.frequencyassignment.constraint;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.algebra.tuples.Tuple2;
import parallelhyflex.genetic.constraint.KeyValueConstraintImplementationBase;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGeneratorBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentWritableEnforceableConstraintGenerator1 extends EnforceableConstraintGeneratorBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWritableEnforceableConstraint1> implements ArgumentCloneable<FrequencyAssignmentProblem, FrequencyAssignmentWritableEnforceableConstraintGenerator1> {

    public FrequencyAssignmentWritableEnforceableConstraintGenerator1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public FrequencyAssignmentWritableEnforceableConstraint1 readAndGenerate(DataInputStream dis) throws IOException {
        dis.readByte();
        Tuple2<Integer, Integer> data = KeyValueConstraintImplementationBase.getInstance().read(dis);
        return new FrequencyAssignmentWritableEnforceableConstraint1(this.getProblem(), data);
    }

    @Override
    public FrequencyAssignmentWritableEnforceableConstraintGenerator1 clone(FrequencyAssignmentProblem argument) {
        return new FrequencyAssignmentWritableEnforceableConstraintGenerator1(argument);
    }
}
