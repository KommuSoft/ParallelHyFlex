package parallelhyflex.problems.threesat;

import parallelhyflex.algebra.IArgumentCloneable;
import parallelhyflex.experiencestorage.WritableSetExperienceStore;

/**
 *
 * @author kommusoft
 */
public class ThreeSatExperience extends WritableSetExperienceStore<ThreeSatSolution,ThreeSatProblem,ThreeSatWritableEnforceableConstraint1> implements IArgumentCloneable<ThreeSatProblem,ThreeSatExperience> {
    
    public ThreeSatExperience (ThreeSatProblem problem, int historySize, int hypothesisSize, int generationSize) {
        super(problem,new ThreeSatInstanceHypothesisGenerator1(problem),historySize,hypothesisSize,generationSize);
    }
    
    public ThreeSatExperience (ThreeSatProblem problem) {
        this(problem,5,20,5);
    }
    
    @Override
    public ThreeSatExperience clone(ThreeSatProblem argument) {
        return new ThreeSatExperience(argument,this.getHistorySize(),this.getHypothesisSize(),this.getGenerationSize());
    }
    
}
