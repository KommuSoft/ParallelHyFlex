package parallelhyflex.problems.frequencyassignment.experience;

import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWriteableEnforceableConstraint1;
import java.util.logging.Level;
import java.util.logging.Logger;
import parallelhyflex.algebra.tuples.Tuple2;
import parallelhyflex.genetic.constraint.ConstraintRepresentationException;
import parallelhyflex.genetic.constraint.KeyValueConstraintImplementationBase;
import parallelhyflex.problemdependent.experience.InstanceHypothesisGeneratorBase;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentInstanceHypothesisGenerator1 extends InstanceHypothesisGeneratorBase<FrequencyAssignmentSolution, FrequencyAssignmentWriteableEnforceableConstraint1, FrequencyAssignmentProblem> {

    public FrequencyAssignmentInstanceHypothesisGenerator1(FrequencyAssignmentProblem problem) {
        super(problem);
    }

    @Override
    public FrequencyAssignmentWriteableEnforceableConstraint1 generate(FrequencyAssignmentSolution variable) {
        Tuple2<Integer, Integer> data;
        try {
            data = KeyValueConstraintImplementationBase.getInstance().generate(variable.getFrequencyAssignment(), this.getProblem().getFrequencies());
        } catch (ConstraintRepresentationException ex) {
            Logger.getLogger(FrequencyAssignmentInstanceHypothesisGenerator1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return new FrequencyAssignmentWriteableEnforceableConstraint1(this.getProblem(), data);
    }
}