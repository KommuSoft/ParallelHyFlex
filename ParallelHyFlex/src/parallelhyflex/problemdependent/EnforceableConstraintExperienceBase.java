package parallelhyflex.problemdependent;

/**
 *
 * @author kommusoft
 */
public abstract class EnforceableConstraintExperienceBase<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>> extends ExperienceBase<TSolution,TProblem> implements EnforceableConstraintExperience<TSolution> {

    public EnforceableConstraintExperienceBase (TProblem problem) {
        super(problem);
    }
    
}
