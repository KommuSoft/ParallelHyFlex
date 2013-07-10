package parallelhyflex.problems.frequencyassignment.experience;

import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWritableEnforceableConstraint1;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.experiencestorage.WritableSetExperienceStore;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentExperience extends WritableSetExperienceStore<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWritableEnforceableConstraint1> implements ArgumentCloneable<FrequencyAssignmentProblem, FrequencyAssignmentExperience> {

    /**
     *
     * @param problem
     * @param historySize
     * @param hypothesisSize
     * @param generationSize
     */
    public FrequencyAssignmentExperience(FrequencyAssignmentProblem problem, int historySize, int hypothesisSize, int generationSize) {
        super(problem, new FrequencyAssignmentInstanceHypothesisGenerator1(problem), historySize, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentExperience(FrequencyAssignmentProblem problem) {
        this(problem, 5, 20, 5);
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public FrequencyAssignmentExperience clone(FrequencyAssignmentProblem argument) {
        return new FrequencyAssignmentExperience(argument, this.getHistorySize(), this.getHypothesisSize(), this.getGenerationSize());
    }
}
