package parallelhyflex.experiencestorage;

import java.util.Comparator;
import parallelhyflex.algebra.IArgumentCloneable;
import parallelhyflex.problemdependent.Problem;
import parallelhyflex.problemdependent.Solution;
import parallelhyflex.problemdependent.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.WritableExperience;

/**
 *
 * @author kommusoft
 */
public class WritableSetExperienceStore<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>, THypothesis extends WritableEnforceableConstraint<TSolution>> extends SetExperienceStore<TSolution,TProblem,THypothesis> implements WritableExperience<TSolution,THypothesis>, IArgumentCloneable<TProblem,WritableSetExperienceStore<TSolution,TProblem,THypothesis>> {
    
    public WritableSetExperienceStore (TProblem problem, InstanceHypothesisGenerator<TSolution,THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator, int historySize, int hypothesisSize, int generationSize) {
        super(problem,hypothesisGenerator,comparator,historySize,hypothesisSize,generationSize);
    }
    
    public WritableSetExperienceStore (TProblem problem, InstanceHypothesisGenerator<TSolution,THypothesis> hypothesisGenerator, int historySize, int hypothesisSize, int generationSize) {
        super(problem,hypothesisGenerator,historySize,hypothesisSize,generationSize);
    }

    public WritableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution,THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator) {
        super(problem,hypothesisGenerator,comparator);
    }
    
    public WritableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution,THypothesis> hypothesisGenerator) {
        super(problem,hypothesisGenerator,SetHypothesisItemComparator1.getInstance());
    }

    @Override
    public WritableSetExperienceStore<TSolution, TProblem, THypothesis> clone(TProblem argument) {
        return new WritableSetExperienceStore<>(argument,this.getHypothesisGenerator(),this.getComparator(),this.getHistorySize(),this.getHypothesisSize(),this.getGenerationSize());
    }
    
}
