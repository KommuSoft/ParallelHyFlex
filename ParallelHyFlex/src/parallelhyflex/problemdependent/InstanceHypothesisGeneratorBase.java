package parallelhyflex.problemdependent;

import parallelhyflex.ProblemPointerBase;
import parallelhyflex.experiencestorage.InstanceHypothesisGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class InstanceHypothesisGeneratorBase<TSolution extends Solution<TSolution>,THypothesis,TProblem extends Problem<TSolution>> extends ProblemPointerBase<TSolution,TProblem> implements InstanceHypothesisGenerator<TSolution,THypothesis> {
    
    public InstanceHypothesisGeneratorBase (TProblem problem) {
        super(problem);
    }
    
}
