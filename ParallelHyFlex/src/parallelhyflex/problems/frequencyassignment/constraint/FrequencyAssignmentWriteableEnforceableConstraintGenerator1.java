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
public class FrequencyAssignmentWriteableEnforceableConstraintGenerator1 extends EnforceableConstraintGeneratorBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWriteableEnforceableConstraint1> implements ArgumentCloneable<FrequencyAssignmentProblem, FrequencyAssignmentWriteableEnforceableConstraintGenerator1> {

    public FrequencyAssignmentWriteableEnforceableConstraintGenerator1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public FrequencyAssignmentWriteableEnforceableConstraint1 readAndGenerate(DataInputStream dis) throws IOException {
        dis.readByte();
        Tuple2<Integer, Integer> data = KeyValueConstraintImplementationBase.getInstance().read(dis);
        return new FrequencyAssignmentWriteableEnforceableConstraint1(this.getProblem(), data);
    }

    @Override
    public FrequencyAssignmentWriteableEnforceableConstraintGenerator1 clone(FrequencyAssignmentProblem argument) {
        return new FrequencyAssignmentWriteableEnforceableConstraintGenerator1(argument);
    }
}
