package parallelhyflex.experiencestorage;

import java.util.Comparator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WritableExperience;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public class WritableSetExperienceStore<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, THypothesis extends WritableEnforceableConstraint<TSolution>> extends SetExperienceStore<TSolution, TProblem, THypothesis> implements WritableExperience<TSolution, THypothesis> {

    public WritableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator, int historySize, int hypothesisSize, int generationSize) {
        super(problem, hypothesisGenerator, comparator, historySize, hypothesisSize, generationSize);
    }

    public WritableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, int historySize, int hypothesisSize, int generationSize) {
        super(problem, hypothesisGenerator, historySize, hypothesisSize, generationSize);
    }

    public WritableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator) {
        super(problem, hypothesisGenerator, comparator);
    }

    public WritableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator) {
        super(problem, hypothesisGenerator, SetHypothesisItemComparator1.getInstance());
    }
}
