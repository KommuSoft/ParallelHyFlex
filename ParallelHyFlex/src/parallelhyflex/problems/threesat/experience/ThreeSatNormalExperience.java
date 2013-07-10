package parallelhyflex.problems.threesat.experience;

import java.util.logging.Logger;
import parallelhyflex.HyperHeuristic;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.experiencestorage.WriteableNormalSetExperienceStore;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraint1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatNormalExperience extends WriteableNormalSetExperienceStore<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1> implements ArgumentCloneable<ThreeSatProblem, ThreeSatNormalExperience> {

    private static final Logger LOG = Logger.getLogger(ThreeSatNormalExperience.class.getName());

    /**
     *
     * @param problem
     * @param hypothesisSize
     * @param generationSize
     */
    public ThreeSatNormalExperience(ThreeSatProblem problem, int hypothesisSize, int generationSize) {
        super(problem, new ThreeSatInstanceHypothesisGenerator1(problem), hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     */
    public ThreeSatNormalExperience(ThreeSatProblem problem) {
        this(problem, HyperHeuristic.SEARCH_SPACE_SIZE, HyperHeuristic.SEARCH_SPACE_GENERATION);
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public ThreeSatNormalExperience clone(ThreeSatProblem argument) {
        return new ThreeSatNormalExperience(argument, this.getHypothesisSize(), this.getGenerationSize());
    }
}
