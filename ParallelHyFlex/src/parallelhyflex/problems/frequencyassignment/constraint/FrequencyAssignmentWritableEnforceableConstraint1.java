package parallelhyflex.problems.frequencyassignment.constraint;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.algebra.tuples.Tuple2;
import parallelhyflex.genetic.constraint.KeyValueConstraintImplementationBase;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentWritableEnforceableConstraint1 extends WritableEnforceableConstraintBase<FrequencyAssignmentSolution, FrequencyAssignmentProblem> {

    private final Tuple2<Integer, Integer> data;

    public FrequencyAssignmentWritableEnforceableConstraint1(Tuple2<Integer, Integer> data, FrequencyAssignmentProblem problem) {
        this(problem, data);
    }

    public FrequencyAssignmentWritableEnforceableConstraint1(FrequencyAssignmentProblem problem, Tuple2<Integer, Integer> data) {
        super(problem);
        this.data = data;
    }

    @Override
    public void enforceTrue(FrequencyAssignmentSolution solution) {
        KeyValueConstraintImplementationBase.getInstance().enforceTrue(this.getProblem().getObserver(solution), data, solution.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }

    @Override
    public void enforceFalse(FrequencyAssignmentSolution solution) {
        KeyValueConstraintImplementationBase.getInstance().enforceFalse(this.getProblem().getObserver(solution), data, solution.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }

    @Override
    public boolean isSatisfied(FrequencyAssignmentSolution solution) {
        return KeyValueConstraintImplementationBase.getInstance().isSatisfied(data, solution.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }

    @Override
    public boolean isNotSatisfied(FrequencyAssignmentSolution solution) {
        return KeyValueConstraintImplementationBase.getInstance().isNotSatisfied(data, solution.getFrequencyAssignment(), this.getProblem().getFrequencies());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(0x01);
        KeyValueConstraintImplementationBase.getInstance().write(data, dos);
    }
}
