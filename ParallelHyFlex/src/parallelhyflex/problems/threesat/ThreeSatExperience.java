package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.ExperienceBase;

/**
 *
 * @author kommusoft
 */
public class ThreeSatExperience extends ExperienceBase<ThreeSatSolution,ThreeSatProblem> {
    
    public ThreeSatExperience (ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void join(ThreeSatSolution solution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void amnesia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
