package parallelhyflex.problems.threesat.experience;

import java.util.logging.Logger;
import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.experiencestorage.WriteableSetExperienceStore;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraint1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatExperience extends WriteableSetExperienceStore<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1> implements ArgumentCloneable<ThreeSatProblem, ThreeSatExperience> {

    /**
     *
     * @param problem
     * @param historySize
     * @param hypothesisSize
     * @param generationSize
     */
    public ThreeSatExperience(ThreeSatProblem problem, int historySize, int hypothesisSize, int generationSize) {
        super(problem, new ThreeSatInstanceHypothesisGenerator1(problem), historySize, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     */
    public ThreeSatExperience(ThreeSatProblem problem) {
        this(problem, 5, 20, 5);
    }

    /**
     *
     * @param argument
     * @return
     */
    @Override
    public ThreeSatExperience clone(ThreeSatProblem argument) {
        return new ThreeSatExperience(argument, this.getHistorySize(), this.getHypothesisSize(), this.getGenerationSize());
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatExperience.class.getName());
}
