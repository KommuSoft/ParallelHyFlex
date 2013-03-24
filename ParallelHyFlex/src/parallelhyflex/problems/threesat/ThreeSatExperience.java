package parallelhyflex.problems.threesat;

import parallelhyflex.experiencestorage.WritableSetExperienceStore;

/**
 *
 * @author kommusoft
 */
public class ThreeSatExperience extends WritableSetExperienceStore<ThreeSatSolution,ThreeSatProblem,ThreeSatWritableEnforceableConstraint1> {
    
    public ThreeSatExperience (ThreeSatProblem problem, int historySize, int hypothesisSize, int generationSize) {
        super(problem,new ThreeSatWritableEnforceableConstraint1(problem),historySize,hypothesisSize,generationSize);
    }
    
}
