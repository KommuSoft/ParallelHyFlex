package parallelhyflex.problems.frequencyassignment.experience;

import java.util.logging.Logger;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.experiencestorage.WriteableNormalSetExperienceStore;
import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWriteableEnforceableConstraint1;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentNormalExperience extends WriteableNormalSetExperienceStore<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWriteableEnforceableConstraint1> implements ArgumentCloneable<FrequencyAssignmentProblem, FrequencyAssignmentNormalExperience> {

    private static final Logger LOG = Logger.getLogger(FrequencyAssignmentNormalExperience.class.getName());

    /**
     *
     * @param problem
     * @param hypothesisSize
     * @param generationSize
     */
    public FrequencyAssignmentNormalExperience(FrequencyAssignmentProblem problem, int hypothesisSize, int generationSize) {
        super(problem, new FrequencyAssignmentInstanceHypothesisGenerator1(problem), hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     */
    public FrequencyAssignmentNormalExperience(FrequencyAssignmentProblem problem) {
        this(problem, HyperHeuristic.SEARCH_SPACE_SIZE, HyperHeuristic.SEARCH_SPACE_GENERATION);
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public FrequencyAssignmentNormalExperience clone(FrequencyAssignmentProblem argument) {
        return new FrequencyAssignmentNormalExperience(argument, this.getHypothesisSize(), this.getGenerationSize());
    }
}
