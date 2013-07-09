package parallelhyflex.problems.threesat.experience;

import parallelhyflex.algebra.ArgumentCloneable;
import parallelhyflex.experiencestorage.WritableSetExperienceStore;
import parallelhyflex.problems.threesat.constraint.ThreeSatWritableEnforceableConstraint1;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatExperience extends WritableSetExperienceStore<ThreeSatSolution, ThreeSatProblem, ThreeSatWritableEnforceableConstraint1> implements ArgumentCloneable<ThreeSatProblem, ThreeSatExperience> {

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
}
